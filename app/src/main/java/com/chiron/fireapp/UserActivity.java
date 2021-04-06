package com.chiron.fireapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.AsyncQueryHandler;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.chiron.fireapp.adapter.UserAdapter;
import com.chiron.fireapp.database.MyDatabase;
import com.chiron.fireapp.database.entity.User;
import com.chiron.fireapp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private MyDatabase myDatabase;
    private List<User> userList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        findViewById(R.id.btnInsertUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddUserDialog();
            }
        });
        ListView lvUser = findViewById(R.id.lvUser);
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(UserActivity.this,userList);
        lvUser.setAdapter(userAdapter);
        lvUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                updateOrDeleteDailog(userList.get(position));
                return false;
            }
        });

        myDatabase = MyDatabase.getInstance(this);
        new QueryUserTask().execute();
    }

    private void updateOrDeleteDailog(final User user){
        final String[] option = new String[]{"更新","删除"};
        new AlertDialog.Builder(UserActivity.this)
                .setTitle("")
                .setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0){
                            openUpdateStudentDialog(user);
                        }else if (which == 1){
                            new DeleteStudentTask(user).execute();
                        }
                    }
                }).show();
    }
    private void openAddUserDialog(){
        View customView = this.getLayoutInflater().inflate(R.layout.dialog_layout_user,null);
        final EditText userName = customView.findViewById(R.id.etName);
        final EditText userPass = customView.findViewById(R.id.etPass);

        final AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Add User");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(TextUtils.isEmpty(userName.getText().toString()) || TextUtils.isEmpty(userPass.getText().toString())){
                    ToastUtil.showToast(UserActivity.this,"输入不能为空");
                }else{
                    new InsertUserTask(userName.getText().toString(),userPass.getText().toString()).execute();
                }
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(customView);
        alertDialog.show();
    }

    private void openUpdateStudentDialog(final User user)
    {
        if (user == null)
        {
            return;
        }
        View customView = this.getLayoutInflater().inflate(R.layout.dialog_layout_user, null);
        final EditText etName = customView.findViewById(R.id.etName);
        final EditText etPass = customView.findViewById(R.id.etPass);
        etName.setText(user.name);
        etPass.setText(user.pass);

        final AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
        AlertDialog dialog = builder.create();
        dialog.setTitle("Update User");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etPass.getText().toString()))
                {
                    Toast.makeText(UserActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else
                {
                    new UpdateStudentTask(user.id, etName.getText().toString(), etPass.getText().toString()).execute();
                }
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        dialog.setView(customView);
        dialog.show();
    }

    private class DeleteStudentTask extends AsyncTask<Void, Void, Void>
    {
        User user;

        public DeleteStudentTask(User user)
        {
            this.user = user;
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            myDatabase.userDao().deleteUser(user);
            userList.clear();
            userList.addAll(myDatabase.userDao().getUserList());
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            userAdapter.notifyDataSetChanged();
        }
    }

    private class UpdateStudentTask extends AsyncTask<Void, Void, Void>
    {
        int id;
        String name;
        String age;

        public UpdateStudentTask(final int id, final String name, final String age)
        {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            myDatabase.userDao().updateUser(new User(id, name, age));
            userList.clear();
            userList.addAll(myDatabase.userDao().getUserList());
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            userAdapter.notifyDataSetChanged();
        }
    }

    private class InsertUserTask extends AsyncTask<Void,Void,Void>{
        String name;
        String pass;

        public InsertUserTask(String name, String pass) {
            this.name = name;
            this.pass = pass;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            userAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            myDatabase.userDao().insertUser(new User(name,pass));
            userList.clear();
            userList.addAll(myDatabase.userDao().getUserList());
            return null;
        }
    }

    private class QueryUserTask extends AsyncTask<Void,Void,Void>{
        public QueryUserTask() {
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            userAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userList.clear();
            userList.addAll(myDatabase.userDao().getUserList());
            return null;
        }
    }
}
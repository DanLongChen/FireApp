package com.chiron.fireapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.chiron.fireapp.database.dao.UserDao;
import com.chiron.fireapp.database.entity.User;

//创建多张表，可以在entities里面加上
@Database(entities = {User.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    private static final String DB_NAME = "my_db";

    private static MyDatabase instandce;

    public static MyDatabase getInstance(Context context) {
        if (instandce == null) {
            synchronized (MyDatabase.class) {
                if (instandce == null) {
                    instandce = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, DB_NAME).build();
                }
            }
        }
        return instandce;
    }

    public abstract UserDao userDao();

}

package com.chiron.fireapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.util.TableInfo;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id",typeAffinity = ColumnInfo.INTEGER)
    public int id;
    @ColumnInfo(name = "name",typeAffinity = ColumnInfo.TEXT)
    public String name;
    @ColumnInfo(name ="pass",typeAffinity = ColumnInfo.TEXT)
    public String pass;

    //room会使用这个构造器
    public User(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    //希望有多个构造器，则可以使用ignore，同样使用ignore标记的字段，room不会进行持久化
    @Ignore
    public User(String name,String pass){
        this.name=name;
        this.pass=pass;
    }
}

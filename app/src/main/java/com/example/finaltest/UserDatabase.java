package com.example.finaltest;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {UserEntity.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static final String dbName = "user";
    private static UserDatabase userDatabase;

    public abstract UserDao userDao();

    public static UserDatabase getUserDatabase(Context context)
    {
        if (userDatabase == null)
        {
            userDatabase = Room.databaseBuilder(context,
                    UserDatabase.class, "Accounts").allowMainThreadQueries().build();
        }
        return userDatabase;
    }




}


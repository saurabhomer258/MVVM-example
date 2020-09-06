package com.example111.saurabhomer.firebaseauth.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example111.saurabhomer.firebaseauth.App;
import com.example111.saurabhomer.firebaseauth.Dao.UserDao;
import com.example111.saurabhomer.firebaseauth.model.UserData;

import static android.arch.persistence.room.Room.databaseBuilder;


@Database(entities = {UserData.class }, version = 1)
public abstract class AppDatabase  extends RoomDatabase {


    private static final String DATABASE_NAME = "personlist";

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {

            instance = Room.databaseBuilder(App.appContext, AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                            .build();
        }
        return instance;
    }
    public abstract UserDao userDao();

}

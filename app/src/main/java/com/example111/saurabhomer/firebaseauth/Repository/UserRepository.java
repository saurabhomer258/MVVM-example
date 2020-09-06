package com.example111.saurabhomer.firebaseauth.Repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.example111.saurabhomer.firebaseauth.DB.AppDatabase;
import com.example111.saurabhomer.firebaseauth.Dao.UserDao;
import com.example111.saurabhomer.firebaseauth.model.UserData;

import java.util.List;

public class UserRepository {
    private LiveData<List<UserData>> mAllUser;
    UserDao userDao;

    public UserRepository(Context context){
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        userDao = appDatabase.userDao();
        mAllUser = userDao.getAllUser();
    }


    public LiveData<List<UserData>> getAllUser() {
        return mAllUser;
    }

    public void insert(UserData userData) {
        new insertAsyncTask(userDao).execute(userData);
    }


    private static class insertAsyncTask extends AsyncTask<UserData, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final UserData... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}


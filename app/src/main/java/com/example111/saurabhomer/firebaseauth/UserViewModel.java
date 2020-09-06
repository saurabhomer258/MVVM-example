package com.example111.saurabhomer.firebaseauth;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example111.saurabhomer.firebaseauth.Repository.UserRepository;
import com.example111.saurabhomer.firebaseauth.model.UserData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;
    private LiveData<List<UserData>> mAllData;
    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllData = mRepository.getAllUser();
    }

    LiveData<List<UserData>> getAllUser() { return mAllData;
    }

    public void insert(UserData userData) { mRepository.insert(userData); }
}

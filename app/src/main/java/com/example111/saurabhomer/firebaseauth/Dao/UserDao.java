package com.example111.saurabhomer.firebaseauth.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example111.saurabhomer.firebaseauth.model.UserData;

import java.util.List;
@Dao
public interface UserDao {

    @Query("SELECT * from UserData")
    LiveData<List<UserData>> getAllUser();

    @Insert
    long  insert(UserData user);


}

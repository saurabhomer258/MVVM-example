package com.example111.saurabhomer.firebaseauth.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity
public class UserData {
    @PrimaryKey(autoGenerate = true)
            @NotNull
      Long id;
      String name;

    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

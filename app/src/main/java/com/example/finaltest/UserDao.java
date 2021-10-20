package com.example.finaltest;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface UserDao {
    @Insert
    void feedbackUser(UserEntity userEntity);
}

package com.example.common.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.common.entities.AppScreenTime;

import java.util.List;

@Dao
public interface AppScreenTimeDao {
    @Insert
    void SaveAppTime(AppScreenTime time);

    @Query("SELECT * FROM screenTime WHERE appName LIKE :appName")
    List<AppScreenTime> getScreenTime(String appName);
}

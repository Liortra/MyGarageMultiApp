package com.example.common.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "screenTime")
public class AppScreenTime {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String appName;
    @ColumnInfo
    public long duration;

    @Ignore
    public AppScreenTime(long duration, String appName) {
        this.appName = appName;
        this.duration = duration;
    }

    public AppScreenTime() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}

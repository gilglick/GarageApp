package com.example.garageapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "session")
public class Session {
    @ColumnInfo(name = "start")
    private long startTime;

    @ColumnInfo(name = "end")
    private long endTime;

    @ColumnInfo(name = "total")
    private long totalTime;

    @PrimaryKey(autoGenerate = true)
    private long id;

    public Session(long startTime){
        this.startTime = startTime;
    }
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

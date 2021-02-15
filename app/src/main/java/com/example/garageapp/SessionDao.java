package com.example.garageapp;

import androidx.room.Dao;
import androidx.room.*;

import java.util.List;

@Dao
public interface SessionDao {
    @Insert
    void insert(Session session);

    @Query("SELECT * FROM Session WHERE start = (SELECT MAX(start) FROM Session)")
    Session getLastSession();

    @Delete
    void delete(Session session);

    @Query("SELECT SUM(total) FROM Session")
    long totalSpendsTime();
}
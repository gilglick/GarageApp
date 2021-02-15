package com.example.garageapp;

import androidx.room.Dao;
import androidx.room.*;

import java.util.List;

@Dao
public interface SessionDao {
    @Insert
    void insert(Session session);

    @Query("SELECT * FROM session WHERE start = (SELECT MAX(start) FROM session)")
    Session getLastSession();

    @Delete
    void delete(Session session);

    @Query("SELECT SUM(total) FROM session")
    long totalSpendsTime();
}
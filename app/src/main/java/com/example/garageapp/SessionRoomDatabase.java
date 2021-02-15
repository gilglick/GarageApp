package com.example.garageapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Database(entities = {Session.class}, version = 1,exportSchema = false)
public abstract class SessionRoomDatabase extends RoomDatabase {
    public abstract SessionDao sessionDao();
    private static volatile SessionRoomDatabase sessionRoomDatabase;
    private static final Lock lock = new ReentrantLock();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService dataWriteExecutorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static SessionRoomDatabase getInstance(final Context context){
        // double check locking for improving performance
        if(sessionRoomDatabase == null){
            try{
                lock.lock();
                if(sessionRoomDatabase  == null){
                    sessionRoomDatabase  = Room.databaseBuilder(
                            context.getApplicationContext(),
                            SessionRoomDatabase.class, "session_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }finally{
                lock.unlock();
            }
        }
        return sessionRoomDatabase;
    }

}

package com.example.garageapp;

import android.app.Application;

public class Repository {

    private final SessionDao sessionDao;

    public Repository(Application application) {
        SessionRoomDatabase sessionRoomDatabase = SessionRoomDatabase.getInstance(application);
        sessionDao = sessionRoomDatabase.sessionDao();
    }

    public void getLastSession(final OnSessionResult<Session> callback) {
        SessionRoomDatabase.dataWriteExecutorService.execute(() -> callback.onSessionResult(sessionDao.getLastSession()));
    }

    public void getTotalTime(final OnSessionResult<Long> callback) {
        SessionRoomDatabase.dataWriteExecutorService.execute(() -> callback.onSessionResult(sessionDao.totalSpendsTime()));
    }

    public void insert(Session session) {
        SessionRoomDatabase.dataWriteExecutorService.execute(() -> sessionDao.insert(session));
    }

    public interface OnSessionResult<T> {
        void onSessionResult(T result);
    }

}

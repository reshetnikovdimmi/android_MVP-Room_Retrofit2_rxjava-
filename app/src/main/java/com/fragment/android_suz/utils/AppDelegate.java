package com.fragment.android_suz.utils;

import android.app.Application;

import androidx.room.Room;

import com.fragment.android_suz.database.MySUZDatabase;
import com.fragment.android_suz.model.stoks.Storage;

public class AppDelegate extends Application {

    private MySUZDatabase mMySUZDatabase;
    private Storage mStorage;

    @Override
    public void onCreate() {
        super.onCreate();

        mMySUZDatabase = Room.databaseBuilder(this, MySUZDatabase.class, "mysuz_database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        mStorage = new Storage(mMySUZDatabase.getMySUZDao());

    }

    public MySUZDatabase getmMySUZDatabase() {
        return mMySUZDatabase;
    }

    public Storage getStorage() {
        return mStorage;
    }

}


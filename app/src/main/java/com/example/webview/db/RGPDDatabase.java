package com.example.webview.db;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;

import java.util.List;

@Database(entities = {Data.class}, version = 1, exportSchema = false)
public abstract class RGPDDatabase extends androidx.room.RoomDatabase {
    private static volatile RGPDDatabase db;

    public abstract DataDao getDataDao();

    public static RGPDDatabase getInstance(final Context context){
        if (db == null){
            synchronized (RGPDDatabase.class){
                if (db == null){
                    db = Room.databaseBuilder(context, RGPDDatabase.class, "db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return db;
    }

    @Dao
    public interface DataDao {
        @Insert
        void insert(Data Data);

        @Query("SELECT * FROM Data")
        LiveData<List<Data>> obtener();
    }
}

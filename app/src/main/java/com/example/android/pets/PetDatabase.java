package com.example.android.pets;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PetEntity.class}, version = 1)
public abstract class PetDatabase extends RoomDatabase {

    private static PetDatabase instance;

    public abstract PetDao petDao();

    public static synchronized PetDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PetDatabase.class,
                    "pet_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
                    .build();
        }
        return instance;
    }

}

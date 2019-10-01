package com.example.android.pets;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private PetDao petDao;
        private PopulateDbAsyncTask(PetDatabase db){
            petDao = db.petDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            petDao.insert(new PetEntity("Toto","Terrier",1,7));
            return null;
        }
    }
}

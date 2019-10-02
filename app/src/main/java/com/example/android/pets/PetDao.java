package com.example.android.pets;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PetDao {
    @Insert
    void insert(PetEntity petEntity);

    @Update
    void update(PetEntity petEntity);

    @Delete
    void delete(PetEntity petEntity);

    @Query("DELETE FROM pet_table")
    void deleteAllPets();

    @Query("SELECT * FROM pet_table ORDER BY name ASC")
    LiveData<List<PetEntity>> getAllPets();

    @Query("SELECT * FROM pet_table WHERE id = :id LIMIT 1")
    PetEntity getPetById(int id);
}

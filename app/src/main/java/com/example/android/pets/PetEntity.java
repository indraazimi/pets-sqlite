package com.example.android.pets;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pet_table")
public class PetEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String breed;
    private int gender;
    private int weight;

    public PetEntity(String name, String breed, int gender, int weight) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.weight = weight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getGender() {
        return gender;
    }

    public int getWeight() {
        return weight;
    }
}

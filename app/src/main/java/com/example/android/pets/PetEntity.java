package com.example.android.pets;

import android.os.Parcel;
import android.os.Parcelable;

public class PetEntity implements Parcelable {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.breed);
        dest.writeInt(this.gender);
        dest.writeInt(this.weight);
    }

    protected PetEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.breed = in.readString();
        this.gender = in.readInt();
        this.weight = in.readInt();
    }

    public static final Parcelable.Creator<PetEntity> CREATOR = new Parcelable.Creator<PetEntity>() {
        @Override
        public PetEntity createFromParcel(Parcel source) {
            return new PetEntity(source);
        }

        @Override
        public PetEntity[] newArray(int size) {
            return new PetEntity[size];
        }
    };
}

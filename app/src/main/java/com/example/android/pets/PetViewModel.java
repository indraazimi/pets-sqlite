package com.example.android.pets;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PetViewModel extends AndroidViewModel {

    private LiveData<List<PetEntity>> allPets;
    private PetDao petDao;

    public PetViewModel(@NonNull Application application) {
        super(application);
        petDao = PetDatabase.getInstance(application).petDao();
        allPets = petDao.getAllPets();
    }

    public void insert(PetEntity petEntity){
        petDao.insert(petEntity);
    }

    public void update(PetEntity petEntity){
        petDao.update(petEntity);
    }

    public void delete(PetEntity petEntity){
        petDao.delete(petEntity);
    }

    public void deleteAll(){
        petDao.deleteAllPets();
    }

    public LiveData<List<PetEntity>> getAllPets(){
        return allPets;
    }

    public PetEntity getPetById(int id){
        return petDao.getPetById(id);
    }

}

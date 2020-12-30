package com.yogdroidtech.dogsapp.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DogDao {

    @Insert
    void insertAll(List<Dog> dogs);

    @Query("SELECT * FROM dog_table")
    List<Dog> getAllDogs();

    @Query("DELETE FROM dog_table")
    void deleteAll();
}

package com.yogdroidtech.dogsapp.model;

import android.content.Context;
import android.icu.text.AlphabeticIndex;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Dog.class}, version = 1, exportSchema = false)
public abstract class DogDatabase extends RoomDatabase {

    public abstract DogDao dogDao();
    private static DogDatabase instance=null;
    public static DogDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),DogDatabase.class,"dog_table").build();
        }
        return instance;
    }
}

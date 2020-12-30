package com.yogdroidtech.dogsapp.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import com.yogdroidtech.dogsapp.model.Dog;
import com.yogdroidtech.dogsapp.model.DogDao;
import com.yogdroidtech.dogsapp.model.DogDatabase;
import com.yogdroidtech.dogsapp.model.RetrofitInstance;
import com.yogdroidtech.dogsapp.model.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListViewModel extends AndroidViewModel {
public MutableLiveData<List<Dog>> dogList = new MutableLiveData<>();
private long refreshTime = 10 * 1000 * 1000 * 1000L;
SharedPreferences sharedPreferences;
DogDao dogDao;
    public ListViewModel(@NonNull Application application) {
        super(application);
        DogDatabase dogDatabase = DogDatabase.getInstance(getApplication());
        dogDao = dogDatabase.dogDao();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
    }

    public void refresh(){
        long currentTime = System.nanoTime();
        long storeTime = sharedPreferences.getLong("storeTime",0L);
        if(currentTime - storeTime>refreshTime){
            loadFromRemote();
            Toast.makeText(getApplication(), "Remote", Toast.LENGTH_SHORT).show();
            Log.i("yogesh","Remote");
        }
        else{
            loadFromDB();
            Toast.makeText(getApplication(), "Database", Toast.LENGTH_SHORT).show();
            Log.i("yogesh","Database");
        }

    }

    public void loadFromDB(){
        retrieveTask retrieveTask = new retrieveTask(dogDao);
        retrieveTask.execute();
        Toast.makeText(getApplication(), "Retrieved", Toast.LENGTH_SHORT).show();
    }

    public void loadFromRemote(){
        Retrofit retrofit = RetrofitInstance.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<List<Dog>> listCall = retrofitInterface.getDogs();
        listCall.enqueue(new Callback<List<Dog>>() {
            @Override
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                dogList.setValue(response.body());

                deleteTask deleteTask = new deleteTask(dogDao);
                deleteTask.execute();

                insertTask insertTask = new insertTask(dogDao);
                insertTask.execute(dogList.getValue());
            }

            @Override
            public void onFailure(Call<List<Dog>> call, Throwable t) {

            }
        });
    }

    class deleteTask extends AsyncTask<Void, Void, Void>{
        DogDao dogDao;

        public deleteTask(DogDao dogDao) {
            this.dogDao = dogDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dogDao.deleteAll();
            return null;
        }
    }

    class retrieveTask extends AsyncTask<Void,Void,List<Dog>>{
        DogDao dogDao;

        public retrieveTask(DogDao dogDao) {
            this.dogDao = dogDao;
        }

        @Override
        protected List<Dog> doInBackground(Void... voids) {

            return dogDao.getAllDogs();
        }

        @Override
        protected void onPostExecute(List<Dog> dogs) {
            dogList.setValue(dogs);
        }
    }

    class insertTask extends AsyncTask<List<Dog>,Void,Void>{
    DogDao dogDao;

        public insertTask(DogDao dogDao) {
            this.dogDao = dogDao;
        }

        @Override
        protected Void doInBackground(List<Dog>... lists) {
            dogDao.insertAll(lists[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            long storeTime = System.nanoTime();
            sharedPreferences.edit().putLong("storeTime",storeTime).apply();
        }
    }
}

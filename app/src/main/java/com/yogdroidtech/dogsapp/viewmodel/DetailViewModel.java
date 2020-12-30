package com.yogdroidtech.dogsapp.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yogdroidtech.dogsapp.model.Dog;
import com.yogdroidtech.dogsapp.model.DogDao;
import com.yogdroidtech.dogsapp.model.DogDatabase;

import java.util.List;

public class DetailViewModel extends AndroidViewModel {
    public MutableLiveData<List<Dog>> dogList = new MutableLiveData<>();
    DogDao dogDao;
    public DetailViewModel(@NonNull Application application) {
        super(application);
        DogDatabase dogDatabase = DogDatabase.getInstance(getApplication());
        dogDao = dogDatabase.dogDao();
    }
    public void getDogs(){
        RetrieveTask retrieveTask = new RetrieveTask(dogDao);
        retrieveTask.execute();
    }

    class RetrieveTask extends AsyncTask<Void,Void,List<Dog>>{
        DogDao dogDao;

        public RetrieveTask(DogDao dogDao) {
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
}

package com.example.mybotany;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class PlantViewModel extends AndroidViewModel {

    // Create a LiveData with a Plant
    private MutableLiveData<Plant> currentPlant;
    private MutableLiveData<ArrayList<Plant>> timerList;

    public ArrayList<Plant> getTimerList() {return timerList.getValue();}

    public void setTimerList(ArrayList<Plant> timerList) {
        this.timerList.setValue(timerList);
    }

    public PlantViewModel(@NonNull Application application) {
        super(application);
        currentPlant = new MutableLiveData<Plant>();
        timerList = new MutableLiveData<ArrayList<Plant>>();
        timerList.setValue(new ArrayList<Plant>());
    }

    public MutableLiveData<Plant> getCurrentPlant() {
        return currentPlant;
    }

    public void setCurrentPlant(Plant currentPlant) {
        this.currentPlant.setValue(currentPlant);
    }
}

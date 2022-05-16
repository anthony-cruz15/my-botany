package com.example.mybotany;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlantViewModel extends AndroidViewModel {

    // Create a LiveData with a Plant
    private MutableLiveData<Plant> currentPlant;
    private MutableLiveData<Plant> newtTimerPlant;

    public MutableLiveData<Plant> getNewtTimerPlant() {
        return newtTimerPlant;
    }

    public void setNewtTimerPlant(Plant newtTimerPlant) {
        this.newtTimerPlant.setValue(newtTimerPlant);
    }

    public PlantViewModel(@NonNull Application application) {
        super(application);
        currentPlant = new MutableLiveData<Plant>();
        newtTimerPlant = new MutableLiveData<Plant>();
    }

    public MutableLiveData<Plant> getCurrentPlant() {
        return currentPlant;
    }

    public void setCurrentPlant(Plant currentPlant) {
        this.currentPlant.setValue(currentPlant);
    }
}

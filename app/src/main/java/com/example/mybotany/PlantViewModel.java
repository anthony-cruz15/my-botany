package com.example.mybotany;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlantViewModel extends AndroidViewModel {

    // Create a LiveData with a String
    private MutableLiveData<Plant> currentPlant;

    public PlantViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Plant> getCurrentPlant() {
        return currentPlant;
    }

    public void setCurrentPlant(Plant currentPlant) {
        this.currentPlant.setValue(currentPlant);
    }
}

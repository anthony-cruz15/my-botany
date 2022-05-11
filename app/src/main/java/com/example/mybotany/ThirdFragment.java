package com.example.mybotany;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ThirdFragment extends Fragment {

    PlantViewModel plantViewModel;

    public ThirdFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        plantViewModel = new ViewModelProvider(getActivity()).get(PlantViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        TextView plantInfoTV = view.findViewById(R.id.plantInfo_textView);
        TextView waterInfoTV = view.findViewById(R.id.waterInfo_textView);
        ImageView bannerImage = view.findViewById(R.id.plant_imageView);
        Plant plant = plantViewModel.getCurrentPlant().getValue();
        plantViewModel.getCurrentPlant().observe(getViewLifecycleOwner(),
                new Observer<Plant>() {
            @Override
            public void onChanged(Plant plant) {
                plantInfoTV.setText(plant.getPlantInfo());
                waterInfoTV.setText(plant.getWaterInfo());
            }
        });
        return view;
    }
}

package com.example.mybotany;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.transition.TransitionInflater;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ThirdFragment extends Fragment {

    PlantViewModel plantViewModel;

    public ThirdFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        plantViewModel = new ViewModelProvider(getActivity()).get(PlantViewModel.class);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setEnterTransition(inflater.inflateTransition(R.transition.slide));
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        TextView plantInfoTV = view.findViewById(R.id.plantInfo_textView);
        TextView waterInfoTV = view.findViewById(R.id.waterInfo_textView);
        ImageView bannerImage = view.findViewById(R.id.plant_imageView);
        FloatingActionButton addTimerButton = view.findViewById(R.id.addTimer_button);
        plantViewModel.getCurrentPlant().observe(getViewLifecycleOwner(),
                new Observer<Plant>() {
            @Override
            public void onChanged(Plant plant) {
                plantInfoTV.setText(plant.getPlantInfo());
                waterInfoTV.setText(plant.getWaterInfo());
                bannerImage.setImageResource(plant.getBannerImage());
                addTimerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        plantViewModel.getTimerList().add(plant);
                        Navigation.findNavController(view).navigate(R.id.action_ThirdFragment_to_FirstFragment);
                    }
                });
            }
        });
        return view;
    }
}

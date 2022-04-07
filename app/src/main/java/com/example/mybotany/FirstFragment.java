package com.example.mybotany;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybotany.databinding.FragmentFirstBinding;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private RecyclerView mRecyclerView;
    private ArrayList<Plant> mPlantData;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        //initializes array list
        mPlantData = new ArrayList<>();

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        //initialize recycler view and sets layout manager
        mRecyclerView = binding.plantTimerList;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return binding.getRoot();

    }

    private void initializeData() {
        //fills array with content from string files
        String[] plantNameList = getResources()
                .getStringArray(R.array.plant_names); //string list of plant names from DB
        String[] plantInfo = getResources()
                .getStringArray(R.array.plant_info); //string list of plant info from DB
        TypedArray plantImageResources = getResources().obtainTypedArray(R.array.plant_images); //list of plant images

        mPlantData.clear();

        for (int i = 0; i < plantNameList.length; i++ ) {
            mPlantData.add(new Plant(plantNameList[i], plantInfo[i], plantImageResources.getResourceId(i, 0)));
        }
        plantImageResources.recycle();
        //mAdapter.notifyDataSetChanged();
    }

    private void resetPlants (View view) {
        initializeData();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
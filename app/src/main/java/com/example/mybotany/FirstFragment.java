package com.example.mybotany;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybotany.databinding.FragmentFirstBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private RecyclerView mRecyclerView;
    private ArrayList<Plant> mPlantData;
    private TimerListAdapter mAdapter;
    private FloatingActionButton addPlantButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //initializes array list
        mPlantData = new ArrayList<>();

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment

        Context context = getContext();
        RecyclerView recyclerView = binding.plantTimerList;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new TimerListAdapter(getPlantsFromDB(), context));
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(context, LinearLayout.VERTICAL);
        recyclerView.addItemDecoration(decoration);




        //initialize recycler view and fab
        mRecyclerView = binding.plantTimerList;
        addPlantButton = binding.addPlantButton;
        //sets layout manager for recycler view
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        //sets on click listener for the fab
        addPlantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        return binding.getRoot();

    }
    

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<Plant> getPlantsFromDB() {
        List<Plant> plants = new ArrayList<>();
        //fills array with content from string files
        String[] plantNameList = PlantDB.plantName; //string list of plant names from DB
        String[] plantInfo = PlantDB.plantInfo; //string list of plant info from DB
        int[] plantImageResources = PlantDB.plantPic; //list of plant images

        for (int i = 0; i < PlantDB.plantName.length; i++) {
            plants.add(new Plant(PlantDB.plantName[i], PlantDB.plantInfo[i], PlantDB.plantPic[i] ));
        }
        return plants;
    }
}
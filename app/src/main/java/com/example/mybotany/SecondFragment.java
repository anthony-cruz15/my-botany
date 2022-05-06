package com.example.mybotany;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybotany.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);

        Context context = getContext();
        RecyclerView recyclerView = binding.plantButtonList;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new ButtonListAdapter(getPlantsFromDB(), context));
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(context, LinearLayout.VERTICAL);
        recyclerView.addItemDecoration(decoration);

        mRecyclerView = binding.plantButtonList;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

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
        for (int i = 0; i < PlantDB.plantName.length; i++) {
            plants.add(new Plant(PlantDB.plantName[i],"", PlantDB.plantInfo[i],"", PlantDB.plantPic[i] ));
        }
        return plants;
    }
}
package com.example.mybotany;


import android.content.res.TypedArray;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybotany.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private FloatingActionButton addPlantButton;

    private RecyclerView mRecyclerView;
    private ArrayList<Plant> mPlantData;
    private AdapterClass mAdapter;

    private final LinkedList<String> mPlantList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize recycler view and sets layout manager
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializes array list
        mPlantData = new ArrayList<>();

        setContentView(R.layout.activity_main);

        addPlantButton = findViewById(R.id.addPlantButton);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        addPlantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               navController.navigate(R.id.action_FirstFragment_to_SecondFragment);

            }
        });
    }


    private void initializeData() {
        String[] plantNameList = getResources()
                .getStringArray(R.array.plant_names);
        String[] plantInfo = getResources()
                .getStringArray(R.array.plant_info);
        TypedArray plantImageResources = getResources().obtainTypedArray(R.array.plant_images);

        mPlantData.clear();

        for (int i = 0; i < plantNameList.length; i++ ) {
            mPlantData.add(new Plant(plantNameList[i], plantInfo[i], plantImageResources.getResourceId(i, 0)));
        }
        plantImageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }

    private void resetPlants (View view) {
        initializeData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
package com.example.mybotany;


import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
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
    private FloatingActionButton addPlantButton;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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

        createNotificationChannel();

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
        for (int i = 0; i < PlantDB.plantName.length; i++) {
            plants.add(new Plant(PlantDB.plantName[i],"", PlantDB.plantInfo[i], PlantDB.plantWaterInfo[i], PlantDB.plantPic[i] ));
        }
        return plants;
    }

    public void sendNotification() {
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }

    public void createNotificationChannel()
    {
        mNotifyManager = (NotificationManager)
                getActivity().getSystemService(NOTIFICATION_SERVICE);
            if (android.os.Build.VERSION.SDK_INT >=
                    android.os.Build.VERSION_CODES.O) {
                // Create a NotificationChannel
                NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                        "Mascot Notification", NotificationManager
                        .IMPORTANCE_HIGH);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(true);
                notificationChannel.setDescription("Notification from Mascot");
                mNotifyManager.createNotificationChannel(notificationChannel);
            }
    }
    private NotificationCompat.Builder getNotificationBuilder() {
        Intent notificationIntent = new Intent(this.getActivity(), FirstFragment.class);
        PendingIntent notificationPendingIntent = PendingIntent.
                getActivity(this.getContext(), NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this.getContext(),
                PRIMARY_CHANNEL_ID).setContentTitle("Water Soon!")
                .setContentText("Your plant timer is about to go off. Water your plant soon.")
                .setSmallIcon(R.drawable.ic_timer_notif)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);
        return notifyBuilder;
    }

}
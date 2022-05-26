package com.example.mybotany;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.transition.TransitionInflater;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ThirdFragment extends Fragment {

    PlantViewModel plantViewModel;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;
    AlarmManager alarmManager;

    public ThirdFragment() {
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        plantViewModel = new ViewModelProvider(getActivity()).get(PlantViewModel.class);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setEnterTransition(inflater.inflateTransition(R.transition.slide));




        alarmManager = (AlarmManager) getActivity().getSystemService
                (ALARM_SERVICE);

        mNotifyManager = (NotificationManager)
                getActivity().getSystemService(NOTIFICATION_SERVICE);


       /* boolean alarm = (PendingIntent.getBroadcast(getContext(), NOTIFICATION_ID,
              notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);*/


        createNotificationChannel();

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
                                sendNotification();
                                Navigation.findNavController(view).navigate(R.id.action_ThirdFragment_to_FirstFragment);


                            }
                        });
                    }
                });
        return view;
    }

    public void sendNotification() {
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
        Intent notifyIntent = new Intent(getContext(), TimerReceiver.class);
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (getContext(), NOTIFICATION_ID, notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;

        long triggerTime = SystemClock.elapsedRealtime()
                + repeatInterval;
        alarmManager.setInexactRepeating
                (AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        triggerTime, repeatInterval,
                        notifyPendingIntent);
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

    private void createNotificationChannel() {
        mNotifyManager = (NotificationManager)
                getActivity().getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Water plant notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notifies on a timer to " +
                    "water plants");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }



}

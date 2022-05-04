package com.example.mybotany;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ButtonListAdapter extends RecyclerView.Adapter<ButtonListAdapter.Viewholder> {
    private List<Plant> mPlantData;
    private Context mContext;


    public ButtonListAdapter(List<Plant> plantData, Context context) {
        this.mPlantData = plantData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ButtonListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_button_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonListAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        Plant selectedPlant = mPlantData.get(position);
        holder.bindTo(selectedPlant);
    }

    @Override
    public int getItemCount() {
        return mPlantData.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        private ImageButton mPlantButton;
        private TextView mPlantName;

        public Viewholder(View itemView) {
            super(itemView);
            mPlantButton = itemView.findViewById(R.id.plant_button);
            mPlantName = itemView.findViewById(R.id.plantName);
        }

        public void bindTo(Plant selectedPlant) {
            mPlantName.setText(selectedPlant.getName());
            mPlantButton.setBackgroundResource(selectedPlant.getImageResource());
            mPlantButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlantViewModel plantViewModel = new ViewModelProvider((ViewModelStoreOwner)mContext).get(PlantViewModel.class);
                    plantViewModel.setCurrentPlant(selectedPlant);
                    Navigation.findNavController(view).navigate(R.id.action_SecondFragment_to_thirdFragment);
                }
            });
        }
    }

}
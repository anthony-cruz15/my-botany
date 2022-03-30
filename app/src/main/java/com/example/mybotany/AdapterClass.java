package com.example.mybotany;

import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.Viewholder> {
    private ArrayList<Plant> mPlantData;
    private Context mContext;


     AdapterClass(Context context, ArrayList<Plant> plantData) {
        this.mContext = context;
        this.mPlantData = plantData;
    }


    @NonNull
    @Override
    public AdapterClass.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_item, parent, false);
    return new Viewholder(view);
    }

  //  @Override
    //public void onBindViewHolder(@NonNull AdapterClass.ViewHolder holder, int position) {
      //  AdapterClass model = modelClassArrayList.get(position)
//    }
  @Override
  public void onBindViewHolder(@NonNull AdapterClass.Viewholder holder, int position) {
      // to set data to textview and imageview of each card layout
      Plant selectedPlant = mPlantData.get(position);
      holder.bindTo(selectedPlant);
  }

    @Override
    public int getItemCount() {
        return mPlantData.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        private TextView mNameText;
        private TextView mInfoText;
        private ImageView mPlantImage;

        public Viewholder(View itemView) {
            super(itemView);
            mPlantImage = itemView.findViewById(R.id.fillerImage);
            mNameText = itemView.findViewById(R.id.plantName_textView);
            mInfoText = itemView.findViewById(R.id.plantTime_textView);

            itemView.setOnClickListener((View.OnClickListener) this);
        }

        public void bindTo(Plant selectedPlant) {
            mNameText.setText(selectedPlant.getName());
            mInfoText.setText(selectedPlant.getTime());
            Glide.with(mContext).load(selectedPlant.getImageResource()).into(mPlantImage);
        }
        
    }
    }

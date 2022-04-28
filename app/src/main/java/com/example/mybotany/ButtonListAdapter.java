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

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
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
                    Navigation.findNavController(view).navigate(R.id.action_SecondFragment_to_thirdFragment);
                }
            });
        }
    }

    public class SelectedPlantViewModel extends ViewModel {

        // Create a LiveData with a String
        private MutableLiveData<String> currentName;
        private MutableLiveData<String> currentInfo;
        private MutableLiveData<String> currentWaterInfo;
        private MutableLiveData<Drawable> currentPic;

        public MutableLiveData<String> getCurrentName() {
            return currentName;
        }

        public void setCurrentName(MutableLiveData<String> currentName) {
            this.currentName = currentName;
        }

        public MutableLiveData<String> getCurrentInfo() {
            return currentInfo;
        }

        public void setCurrentInfo(MutableLiveData<String> currentInfo) {
            this.currentInfo = currentInfo;
        }

        public MutableLiveData<String> getCurrentWaterInfo() {
            return currentWaterInfo;
        }

        public void setCurrentWaterInfo(MutableLiveData<String> currentWaterInfo) {
            this.currentWaterInfo = currentWaterInfo;
        }

        public MutableLiveData<Drawable> getCurrentPic() {
            return currentPic;
        }

        public void setCurrentPic(MutableLiveData<Drawable> currentPic) {
            this.currentPic = currentPic;
        }
    }
}
package com.example.mybotany;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TimerListAdapter extends RecyclerView.Adapter<TimerListAdapter.Viewholder> {
    private List<Plant> mPlantData;
    private Context mContext;


     public TimerListAdapter(List<Plant> plantData, Context context) {
        this.mPlantData = plantData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public TimerListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_timer_item, parent, false);
        return new Viewholder(view);
    }

  @Override
  public void onBindViewHolder(@NonNull Viewholder holder, int position) {

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
        private CardView mTimerCardView;

        public Viewholder(View itemView) {
            super(itemView);
            mPlantImage = itemView.findViewById(R.id.fillerImage);
            mNameText = itemView.findViewById(R.id.plantName_textView);
            mInfoText = itemView.findViewById(R.id.plantTime_textView);
            mTimerCardView = itemView.findViewById(R.id.timer_cardView);
        }

        public void bindTo(Plant selectedPlant) {
            mNameText.setText(selectedPlant.getName());
            mInfoText.setText(selectedPlant.getTime());
            Glide.with(mContext).load(selectedPlant.getImageResource()).into(mPlantImage);
            mTimerCardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mPlantData.remove(selectedPlant);
                    notifyItemRemoved(getLayoutPosition());
                    return true;
                }
            });
        }
        
    }
}

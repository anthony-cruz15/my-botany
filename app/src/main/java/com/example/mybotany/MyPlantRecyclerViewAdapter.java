package com.example.mybotany;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mybotany.placeholder.PlaceholderContent.Plant;
import com.example.mybotany.databinding.FragmentPlantListBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Plant}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPlantRecyclerViewAdapter extends RecyclerView.Adapter<MyPlantRecyclerViewAdapter.ViewHolder> {

    private final List<Plant> mPlantButtonInfo;

    public MyPlantRecyclerViewAdapter(List<Plant> items) {
        mPlantButtonInfo = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentPlantListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mPlantButtonInfo.get(position);
    }

    @Override
    public int getItemCount() {
        return mPlantButtonInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Plant mItem;

        public ViewHolder(FragmentPlantListBinding binding) {
            super(binding.getRoot());
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
package com.kamel.akra.ui.radio.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kamel.akra.R;
import com.kamel.akra.databinding.ItemRadioBinding;
import com.kamel.akra.ui.radio.model.RadiosItem;

import java.util.List;

public class RadioChannelsAdapter extends RecyclerView.Adapter<RadioChannelsAdapter.ViewHolder> {

    List<RadiosItem> radiosItemList;

    OnItemClickListener onPlayClickListener;
    OnItemClickListener onStopClickListener;

    public void setOnPlayClickListener(OnItemClickListener onPlayClickListener) {
        this.onPlayClickListener = onPlayClickListener;
    }

    public void setOnStopClickListener(OnItemClickListener onStopClickListener) {
        this.onStopClickListener = onStopClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int pos, RadiosItem channel);
    }


    public RadioChannelsAdapter(List<RadiosItem> radiosItemList) {
        this.radiosItemList = radiosItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_radio,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final RadiosItem radiosItem = radiosItemList.get(position);
        viewHolder.binding.txtName.setText(radiosItem.getName());
        if(onPlayClickListener!=null)
            viewHolder.binding.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPlayClickListener.onItemClick(position,radiosItem);
                }
            });

        if(onStopClickListener!=null)
            viewHolder.binding.stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStopClickListener.onItemClick(position,radiosItem);
                }
            });

    }

    public void changeData(List<RadiosItem> items){
        radiosItemList = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (radiosItemList == null) return 0;
        return radiosItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemRadioBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bind();
        }


        void bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView);
            }
        }

        void unbind() {
            if (binding != null) {
                binding.unbind();
            }
        }
    }
}
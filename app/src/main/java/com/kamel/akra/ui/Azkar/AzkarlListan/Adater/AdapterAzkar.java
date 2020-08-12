package com.kamel.akra.ui.Azkar.AzkarlListan.Adater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kamel.akra.R;
import com.kamel.akra.databinding.ItemAzkarListanBinding;
import com.kamel.akra.ui.Azkar.AzkarlListan.model.AzkarItemData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterAzkar extends RecyclerView.Adapter<AdapterAzkar.ViewHolder> {

    List<AzkarItemData> azkarListanResponses;


    public AdapterAzkar(List<AzkarItemData> azkarListanResponses) {
        this.azkarListanResponses = azkarListanResponses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_azkar_listan, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final AzkarItemData azkarListanResponse = azkarListanResponses.get(position);

        viewHolder.itemAzkarListanBinding.itemAzkarTvAzkarName.setText(azkarListanResponse.getReaderName());
        viewHolder.itemAzkarListanBinding.itemAzkarTvLeaderName.setText(azkarListanResponse.getName());
        Picasso.get()
                .load(azkarListanResponse.getReaderImg())
                .placeholder(R.drawable.mic_50)
                .into(viewHolder.itemAzkarListanBinding.itemAzkarImgReader);

        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(v ->
                    onItemClickListener.onItemClick(position, azkarListanResponse));
        }

    }

    @Override
    public int getItemCount() {
        if (azkarListanResponses == null)
            return 0;
        return azkarListanResponses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemAzkarListanBinding itemAzkarListanBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bind();
        }


        void bind() {
            if (itemAzkarListanBinding == null) {
                itemAzkarListanBinding = DataBindingUtil.bind(itemView);
            }
        }

        void unbind() {
            if (itemAzkarListanBinding != null) {
                itemAzkarListanBinding.unbind();
            }
        }

    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, AzkarItemData azkarResponse);
    }

}
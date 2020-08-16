package com.kamel.akra.ui.ListanElmoshaf.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kamel.akra.R;
import com.kamel.akra.databinding.ItemQuranListanBinding;
import com.kamel.akra.ui.ListanElmoshaf.model.ResponseElmoshaf;

import java.util.List;

public class AdapterListanElmoshaf extends RecyclerView.Adapter<AdapterListanElmoshaf.ViewHolder> {

    List<ResponseElmoshaf> ResponseElmoshaf;


    public AdapterListanElmoshaf(List<ResponseElmoshaf> responseElmoshafs) {
        this.ResponseElmoshaf = responseElmoshafs;
    }

    @NonNull
    @Override
    public AdapterListanElmoshaf.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quran_listan, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListanElmoshaf.ViewHolder viewHolder, final int position) {
        final ResponseElmoshaf responseElmoshaf = ResponseElmoshaf.get(position);

        viewHolder.itemQuranListanBinding.itemQuranNumberElsora.setText(responseElmoshaf.getSoraNumber());
        viewHolder.itemQuranListanBinding.idQuranText.setText(responseElmoshaf.getSora());
        viewHolder.itemQuranListanBinding.idMakanElsora.setText(responseElmoshaf.getType());


        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(v ->
                    onItemClickListener.onItemClick(position, responseElmoshaf));
        }

    }

    @Override
    public int getItemCount() {
        if (ResponseElmoshaf == null)
            return 0;
        return ResponseElmoshaf.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemQuranListanBinding itemQuranListanBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bind();
        }


        void bind() {
            if (itemQuranListanBinding == null) {
                itemQuranListanBinding = DataBindingUtil.bind(itemView);
            }
        }

        void unbind() {
            if (itemQuranListanBinding != null) {
                itemQuranListanBinding.unbind();
            }
        }

    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, ResponseElmoshaf responseElmoshaf);
    }

}
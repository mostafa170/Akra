package com.kamel.akra.ui.elmoshaf.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kamel.akra.R;
import com.kamel.akra.databinding.ItemQuranBinding;

public class QuranListAdapter extends RecyclerView.Adapter<QuranListAdapter.ViewHolder> {
    public String[] listOfSewarNames;
    onItemClickListener onTextClickListener;

    public void setListOfSewarNames(String[] listOfSewarNames) {
        this.listOfSewarNames = listOfSewarNames;
    }

    public String[] getListOfSewarNames() {
        return listOfSewarNames;
    }

    public QuranListAdapter(String[] listOfSewarNames) {
        this.listOfSewarNames = listOfSewarNames;
    }

    public void setOnTextClickListener(onItemClickListener onTextClickListener) {
        this.onTextClickListener = onTextClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quran, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        viewHolder.binding.quranText.setText(listOfSewarNames[position]);

        if (onTextClickListener != null) {
            viewHolder.binding.quranText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTextClickListener.onItemClick(position, listOfSewarNames[position]);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listOfSewarNames.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemQuranBinding binding;

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

    public interface onItemClickListener {
        void onItemClick(int position, String suraName);
    }


}
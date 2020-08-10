package com.kamel.akra.ui.elmoshaf.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kamel.akra.R;
import com.kamel.akra.databinding.ItemQuranContentBinding;

import java.util.ArrayList;

public class QuranContentAdapter extends RecyclerView.Adapter<QuranContentAdapter.ViewHolder> {
    ArrayList<String> listOfSouraLines;


    public QuranContentAdapter(ArrayList<String> listOfSouraLines) {
        this.listOfSouraLines = listOfSouraLines;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quran_content,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.binding.quranContent.setText( listOfSouraLines.get(position));
        viewHolder.binding.ayaNumber.setText(position+1+"");


    }

    @Override
    public int getItemCount() {
        return listOfSouraLines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemQuranContentBinding binding;

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
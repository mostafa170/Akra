package com.kamel.akra.ui.Azkar.Azkar.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kamel.akra.R;
import com.kamel.akra.databinding.AzkarItemBinding;


public class azkarListAdapter extends RecyclerView.Adapter<azkarListAdapter.ViewHolder> {
    public String[] listOfAzkarNames;
    onItemClickListener onTextClickListener;


    public azkarListAdapter(String[] listOfAzkarNames) {
        this.listOfAzkarNames = listOfAzkarNames;
    }

    public String[] getListOfAzkarNames() {
        return listOfAzkarNames;
    }

    public void setListOfAzkarNames(String[] listOfAzkarNames) {
        this.listOfAzkarNames = listOfAzkarNames;
    }

    public onItemClickListener getOnTextClickListener() {
        return onTextClickListener;
    }

    public void setOnTextClickListener(onItemClickListener onTextClickListener) {
        this.onTextClickListener = onTextClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.azkar_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.binding.azkarText.setText(listOfAzkarNames[position]);

        if (onTextClickListener!=null){
            viewHolder.binding.azkarText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTextClickListener.onItemClick(position, listOfAzkarNames[position]);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listOfAzkarNames.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AzkarItemBinding binding;

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
        void onItemClick(int position, String azkarName);
    }
}

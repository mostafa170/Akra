package com.kamel.akra.ui.ahadeth.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kamel.akra.R;
import com.kamel.akra.databinding.AhdethContentItemBinding;

public class QuotesListAdapter  extends RecyclerView.Adapter<QuotesListAdapter.ViewHolder> {
    public String[] listOfAhadethNames;
    onItemClickListener onTextClickListener;

    public QuotesListAdapter(String[] listOfAhadethNames) {
        this.listOfAhadethNames = listOfAhadethNames;
    }

    public String[] getListOfAhadethNames() {
        return listOfAhadethNames;
    }

    public void setListOfAhadethNames(String[] listOfAhadethNames) {
        this.listOfAhadethNames = listOfAhadethNames;
    }

    public void setOnTextClickListener(onItemClickListener onTextClickListener) {
        this.onTextClickListener = onTextClickListener;
    }

    public onItemClickListener getOnTextClickListener() {
        return onTextClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ahdeth_content_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.binding.quotesText.setText(listOfAhadethNames[position]);

        if (onTextClickListener!=null){
            viewHolder.binding.quotesText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTextClickListener.onItemClick(position, listOfAhadethNames[position]);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listOfAhadethNames.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AhdethContentItemBinding binding;

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
        void onItemClick(int position, String hadethName);
    }

}
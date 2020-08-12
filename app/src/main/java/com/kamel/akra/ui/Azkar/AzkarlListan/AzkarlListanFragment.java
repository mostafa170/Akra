package com.kamel.akra.ui.Azkar.AzkarlListan;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.kamel.akra.R;
import com.kamel.akra.base.BaseFragment;
import com.kamel.akra.databinding.FragmentAzkarlListanBinding;
import com.kamel.akra.ui.Azkar.AzkarlListan.Adater.AdapterAzkar;
import com.kamel.akra.ui.Azkar.AzkarlListan.model.AzkarItemData;

import java.util.List;


public class AzkarlListanFragment extends BaseFragment {

    View view;
    FragmentAzkarlListanBinding binding;
    AdapterAzkar adapterAzkar;
    private AzkarListanViewModel azkarListanViewModel;

    public AzkarlListanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_azkarl_listan, container, false);
        view = binding.getRoot();

        azkarListanViewModel = new ViewModelProvider(this).get(AzkarListanViewModel.class);
        adapterAzkar = new AdapterAzkar(null);
        binding.azkarRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.azkarRecyclerView.setAdapter(adapterAzkar);

        azkarListanViewModel.getAzkarListan();


        ////////////////request//////////
        azkarListanViewModel.getErrorMessage().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    Snackbar.with(getContext(), null)
                            .type(Type.ERROR)
                            .message("error in response data")
                            .duration(Duration.LONG)
                            .fillParent(true)
                            .textAlign(Align.CENTER)
                            .show();

                } else {
                    Snackbar.with(getContext(), null)
                            .type(Type.ERROR)
                            .message("error in Network")
                            .duration(Duration.LONG)
                            .fillParent(true)
                            .textAlign(Align.CENTER)
                            .show();
                }
            }
        });

        azkarListanViewModel.getShowAzakar().observe(getViewLifecycleOwner(), new Observer<List<AzkarItemData>>() {
            @Override
            public void onChanged(@Nullable List<AzkarItemData> azkarItemData) {
                //show data in recyclerView
                adapterAzkar = new AdapterAzkar(azkarItemData);
                binding.azkarRecyclerView.setAdapter(adapterAzkar);
                adapterAzkar.setOnItemClickListener(new AdapterAzkar.OnItemClickListener() {
                    @Override
                    public void onItemClick(int pos, AzkarItemData Message) {
                        Intent intent = new Intent(getContext(), ListanActivty.class);

                        intent.putExtra("url", String.valueOf(Message.getLink()));
                        Log.e("xxx",String.valueOf(Message.getLink()));
                        intent.putExtra("nameLeader", String.valueOf(Message.getReaderName()));
                        intent.putExtra("nameAzkar", String.valueOf(Message.getName()));
                        startActivity(intent);
                    }
                });
            }

        });
        return view;
    }


}
package com.kamel.akra.ui.ListanElmoshaf;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.kamel.akra.R;
import com.kamel.akra.base.BaseActivity;
import com.kamel.akra.databinding.ActivityListanElmoshafBinding;
import com.kamel.akra.ui.Azkar.AzkarlListan.ListanActivty;
import com.kamel.akra.ui.ListanElmoshaf.adapter.AdapterListanElmoshaf;
import com.kamel.akra.ui.ListanElmoshaf.model.DataItem;
import com.kamel.akra.ui.home.MainActivity;

import java.util.List;

public class ListanElmoshafActivity extends BaseActivity {

    ActivityListanElmoshafBinding binding;
    AdapterListanElmoshaf adapter;
    private ListanElmoshafViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listan_elmoshaf);
        getSupportActionBar().hide();

        binding.activityRadioBack.setOnClickListener(view -> {
            Intent intent = new Intent(ListanElmoshafActivity.this, MainActivity.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(ListanElmoshafViewModel.class);
        adapter = new AdapterListanElmoshaf(null);
        binding.recElmoshafListan.setLayoutManager(new LinearLayoutManager(this));
        binding.recElmoshafListan.setAdapter(adapter);

        viewModel.getElmoshafListan("ar",3);

        ////////////////request//////////
        viewModel.getErrorMessage().observe(this, integer -> {
            if (integer == 1) {
                Snackbar.with(ListanElmoshafActivity.this, null)
                        .type(Type.ERROR)
                        .message("error in response data")
                        .duration(Duration.LONG)
                        .fillParent(true)
                        .textAlign(Align.CENTER)
                        .show();

            } else {
                Snackbar.with(ListanElmoshafActivity.this, null)
                        .type(Type.ERROR)
                        .message("error in Network")
                        .duration(Duration.LONG)
                        .fillParent(true)
                        .textAlign(Align.CENTER)
                        .show();
            }
        });

        viewModel.getShowSora().observe(this, new Observer<List<DataItem>>() {
            @Override
            public void onChanged(@Nullable List<DataItem> responseElmoshafs) {
                if (responseElmoshafs == null) {
                    //errorMessage if data coming is null;
                    binding.tvListEmpty.setVisibility(View.VISIBLE);
                } else {
                    //show data in recyclerView
                    adapter = new AdapterListanElmoshaf(responseElmoshafs);
                    binding.recElmoshafListan.setAdapter(adapter);
                    adapter.setOnItemClickListener(new AdapterListanElmoshaf.OnItemClickListener() {
                        @Override
                        public void onItemClick(int pos, DataItem Message) {
                            Intent intent = new Intent(ListanElmoshafActivity.this, ListanQuranActivty.class);

                            intent.putExtra("url", String.valueOf(Message.getLink()));
                            intent.putExtra("nameLeader", String.valueOf(Message.getReaderName()));
                            intent.putExtra("nameSora", String.valueOf(Message.getSora()));
                            startActivity(intent);
                        }

                    });
                }
            }
        });
    }
}
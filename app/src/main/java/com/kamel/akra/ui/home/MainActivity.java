package com.kamel.akra.ui.home;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.kamel.akra.R;
import com.kamel.akra.base.BaseActivity;
import com.kamel.akra.databinding.ActivityMainBinding;
import com.kamel.akra.ui.Azkar.SwitchActivity;
import com.kamel.akra.ui.ahadeth.AhadethActivity;
import com.kamel.akra.ui.elmoshaf.ElmoshafActivity;
import com.kamel.akra.ui.radio.RadioActivity;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportActionBar().hide();

        binding.mainActivityBtnRadio.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this, RadioActivity.class);
            startActivity(intent);
        });

        binding.mainActivityBtnAhdes.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this, AhadethActivity.class);
            startActivity(intent);
        });

        binding.mainActivityQuranBtn.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this, ElmoshafActivity.class);
            startActivity(intent);
        });

        binding.mainActivityBtnAzkar.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this, SwitchActivity.class);
            startActivity(intent);
        });
    }
}
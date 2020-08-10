package com.kamel.akra.ui.qablaWSebha.sebha;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.kamel.akra.R;
import com.kamel.akra.base.BaseFragment;


/**
 */
public class MentionFragment extends BaseFragment implements View.OnClickListener {
    ImageView refresh, clickTasbeh;
    TextView tasbehCount, sumCount;
    Spinner spinner;
    View view;
    int count = 0;
    int sumCounter = 0;


    public MentionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_mention, container, false);
        tasbehCount = view.findViewById(R.id.tsbeha_count);
        sumCount = view.findViewById(R.id.tsbeha_countSum);
        refresh = view.findViewById(R.id.refresh_ic);
        clickTasbeh = view.findViewById(R.id.sebha_img);
        spinner = view.findViewById(R.id.tsebh_spinner);
        clickTasbeh.setOnClickListener(this);
        refresh.setOnClickListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count = 0;
                tasbehCount.setText(count + "");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sebha_img:
                count++;
                sumCounter++;
                tasbehCount.setText(count + "");
                sumCount.setText(sumCounter + "");
                break;
            case R.id.refresh_ic:
                count = 0;
                sumCounter = 0;
                tasbehCount.setText(count + "");
                sumCount.setText(sumCounter + "");
                break;


        }
    }
    }



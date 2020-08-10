package com.kamel.akra.ui.Azkar.Azkar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kamel.akra.R;
import com.kamel.akra.base.BaseFragment;
import com.kamel.akra.databinding.FragmentAzkarBinding;
import com.kamel.akra.ui.Azkar.Azkar.Adapter.azkarListAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;


public class AzkarFragment extends BaseFragment {
    View view;

    InputStream inputStream;
    BufferedReader bufferedReader;
    int countLinesOfText = 0;
    static ArrayList<String> azkarData;
    azkarListAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    FragmentAzkarBinding binding;


    public static String[] listOfAzkarNames = {"اذكار الصباح","اذكار المساء","اذكار النوم"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_azkar, container, false);

        //listOfHadethName = new ArrayList<>();
        azkarData = txtReader("azkar.txt");
        adapter =  new azkarListAdapter(listOfAzkarNames);
        layoutManager =new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL,false);
        binding.azkarRecyclerView.setAdapter(adapter);
        binding.azkarRecyclerView.setLayoutManager(layoutManager);

        adapter.setOnTextClickListener((position, hadethName) -> showConfirmationMessage(hadethName, azkarData.get(position), R.string.ok, new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        }));

        return view;
    }

    public ArrayList<String> txtReader(String fileName) {
        try {
            inputStream = Objects.requireNonNull(getActivity()).getAssets().open(fileName);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String lineIs = null;
            ArrayList<String> azkar = new ArrayList<>(countLinesOfText);
            try {
                while ((lineIs = bufferedReader.readLine()) != null) {
                    String total=lineIs;
                    while ((lineIs = bufferedReader.readLine()) != null) {
                        if("#".equals(lineIs.trim()))break;
                        total=total+"\n"+lineIs;
                    }
                    azkar.add(total);

                }
                azkarData=azkar;


            } catch (Exception f) {
                f.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return azkarData;
    }
}
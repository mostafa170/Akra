package com.kamel.akra.ui.ahadeth;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kamel.akra.R;
import com.kamel.akra.base.BaseActivity;
import com.kamel.akra.databinding.ActivityAhadethBinding;
import com.kamel.akra.ui.ahadeth.adapter.QuotesListAdapter;
import com.kamel.akra.ui.home.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class AhadethActivity extends BaseActivity {
    QuotesListAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    static ArrayList<String> hadethData;
    ActivityAhadethBinding binding;

    public static String[] listOfAhadethNames = {"الحديث الأول", "الحديث الثاني", "الحديث الـثـالـث", "الحديث الـرابع", "الحديث الخامس", "الحديث السادس", "الحديث السابع", "الحديث الثامن", "الحديث التاسع", "الحديث العاشر",
            "الحديث الحادي عشر", "الحديث الثانى عشر", "الحديث الثالث عشر", "الحد يث الرابع عشر", "الحديث الخامس عشر", "الحديث السادس عشر", "الحديث السابع عشر", "الحد يث الثامن عشر", "الحد يث التاسع عشر", "الحديث العشرون",
            "الحديث الحادي والعشرون", "الحديث الثانى والعشرون", "الحديث الثالث والعشرون", "الحديث الرابع والعشرون", "الحديث الخامس والعشرون", "الحديث السادس والعشرون", "الحديث السابع والعشرون", "الحديث الثامن والعشرون", "الحديث التاسع والعشرون", "الحديث الثلاثون",
            "الحديث الحادي والثلاثون", "الحديث الثانى والثلاثون", "الحديث الثالث والثلاثون", "الحديث الرابع والثلاثون", "الحديث الخامس والثلاثون", "الحديث السادس والثلاثون", "الحديث السابع والثلاثون", "الحديث الثامن والثلاثون", "الحديث التاسع والثلاثون", "الحديث الأربعون",
            "الحديث الحادي والأربعون", "الحديث الثانى والأربعون", "الحديث الثالث والأربعون", "الحديث الرابع والأربعون", "الحديث الخامس والأربعون", "الحديث السادس والأربعون", "الحديث السابع والأربعون", "الحديث الثامن والأربعون", "الحديث التاسع والأربعون", "الحديث الخمسون",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ahadeth);
        getSupportActionBar().hide();


        hadethData = txtReader("ahadeth.txt");
        adapter = new QuotesListAdapter(listOfAhadethNames);
        layoutManager = new GridLayoutManager(this, 3);
        binding.quotesRecyclerView.setAdapter(adapter);
        binding.quotesRecyclerView.setLayoutManager(layoutManager);

        adapter.setOnTextClickListener((position, hadethName) -> showConfirmationMessage(hadethName, hadethData.get(position), R.string.ok, new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();

            }
        }));

        binding.activityRadioBack.setOnClickListener(view -> {
            Intent intent=new Intent(AhadethActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    InputStream inputStream;
    BufferedReader bufferedReader;
    int countLinesOfText = 0;


    public ArrayList<String> txtReader(String fileName) {
        try {
            inputStream = Objects.requireNonNull(AhadethActivity.this).getAssets().open(fileName);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String lineIs = null;
            ArrayList<String> hadeth = new ArrayList<>(countLinesOfText);
            try {
                while ((lineIs = bufferedReader.readLine()) != null) {
                    String total = lineIs;
                    while ((lineIs = bufferedReader.readLine()) != null) {
                        if ("#".equals(lineIs.trim())) break;
                        total = total + "\n" + lineIs;
                    }
                    hadeth.add(total);

                }
                hadethData = hadeth;


            } catch (Exception f) {
                f.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hadethData;
    }
}
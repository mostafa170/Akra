package com.kamel.akra.ui.elmoshaf;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.kamel.akra.R;
import com.kamel.akra.base.BaseActivity;
import com.kamel.akra.databinding.ActivitySouraContentBinding;
import com.kamel.akra.ui.elmoshaf.adapter.QuranContentAdapter;
import com.kamel.akra.ui.home.MainActivity;
import com.kamel.akra.ui.radio.RadioActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SouraContentActivity extends BaseActivity {
    QuranContentAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    static String suraFileName;
    ActivitySouraContentBinding binding;


    public static void setSuraFileName(String suraFileName) {
        SouraContentActivity.suraFileName = suraFileName;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_soura_content);
        getSupportActionBar().hide();

        int suraIndex=Integer.parseInt(suraFileName)-1;
        getSupportActionBar().setTitle( "سورة "+ElmoshafActivity.listOfSewarNames[suraIndex]);
        adapter = new QuranContentAdapter(txtReader());
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        binding.ayatContentRecyclerView.setAdapter(adapter);
        binding.ayatContentRecyclerView.setLayoutManager(layoutManager);

        binding.activityRadioBack.setOnClickListener(view -> {
            Intent intent=new Intent(SouraContentActivity.this, ElmoshafActivity.class);
            startActivity(intent);
        });
    }

    InputStream inputStream, streamCountLines;
    BufferedReader bufferedReader, readerCountLines;
    int countLinesOfText = 0;
    ArrayList<String> suraContent;

    public ArrayList<String> txtReader() {
        try {
            // count the file lines
            streamCountLines = this.getAssets().open(SouraContentActivity.suraFileName + ".txt");
            readerCountLines = new BufferedReader(new InputStreamReader(streamCountLines));
            try {
                while (readerCountLines.readLine() != null) {
                    countLinesOfText++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

// now you know how many lines in the text
            // then start to arrange the text in an array list to pass it to the adapter
            inputStream = this.getAssets().open(SouraContentActivity.suraFileName + ".txt");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                // create the array list size with numbers of lines you count
                suraContent = new ArrayList<>(countLinesOfText);
                for (int i = 0; i < countLinesOfText; i++) {
                    suraContent.add(bufferedReader.readLine());
                }
            } catch (Exception f) {
                f.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return suraContent;
    }
}
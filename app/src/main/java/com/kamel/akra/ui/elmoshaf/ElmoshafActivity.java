package com.kamel.akra.ui.elmoshaf;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.kamel.akra.R;
import com.kamel.akra.base.BaseActivity;
import com.kamel.akra.databinding.ActivityElmoshafBinding;
import com.kamel.akra.ui.elmoshaf.adapter.QuranListAdapter;
import com.kamel.akra.ui.home.MainActivity;
import com.kamel.akra.ui.radio.RadioActivity;

public class ElmoshafActivity extends BaseActivity {

    ActivityElmoshafBinding binding;
    QuranListAdapter adapter ;
    RecyclerView.LayoutManager layoutManager;

    public static String[] listOfSewarNames ={"الفاتحة","البقرة","آل عِمْران","النسَاء","المائدة","الأنعَام","الأعراف","الأنفال","التوبَة","يونس","هُود"
            ,"یوسُف","الرّعد","إبراهيم","الحِجر","النحل","الإسرَاء","الكهف","مَریم","طه","الأنبیَاء","الحَج","المؤمِنون"
            ,"النّور","الفُرقان","الشُّعَراء","النّمل","القصص","العنكبوت","الرّوم","لقمان","السجدة","الأحزاب","سبأ"
            ,"فاطِر","يس","الصّافّات","صۤ","الزمَر","غافِر","فصّلت","الشورى","الزخرف","الدخَان","الجاثيَة","الأحقاف"
            ,"محمد","الفتح","الحجرات","قۤ","الذاريات","الطور","النجم","القمَر","الرحمن","الواقعة","الحديد","المجادلة"
            ,"الحشر","الممتحنة","الصّف","الجمعة","المنافقون","التغابُن","الطلاق","التحريم","الملك","القلم","الحاقة","المعَارِج"
            ,"نوح","الجِنّ","المزمّل","المدّثر","القيَامة","الإنسان","المرسَلات","النّبَأ","النّازعَات","عبَس","التكوير","الإنفطار"
            ,"المطفّفين","الانشِقَاق","البُروج","الطّارق","الأعلىٰ","الغَاشِيَة","الفجْر","البَلد","الشمس","الليل","الضّحَىٰ","الشّرْح"
            ,"التين","العَلَق","القدْر","البیّنة","الزَّلزّلة","العَاديَات","القارعَة","التكاثر","العَصر",
            "الهُمَزة","الفِیل","قريش","الماعُون","الكوثر","الكافِرون","النّصر","المسَد","الإخلاص","الفَلَق","الناس"};

    public String[] getListOfSewarNames() {
        return listOfSewarNames;
    }

    public void setListOfSewarNames(String[] listOfSewarNames) {
        this.listOfSewarNames = listOfSewarNames;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_elmoshaf);
        getSupportActionBar().hide();

        adapter = new QuranListAdapter(listOfSewarNames);
        binding.quranRecyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL,false);

        binding.quranRecyclerView.setLayoutManager(layoutManager);
        adapter.setOnTextClickListener(new QuranListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, String suraName) {
                SouraContentActivity.setSuraFileName(String.valueOf(position + 1));
                Intent intent = new Intent(ElmoshafActivity.this , SouraContentActivity.class);
                startActivity(intent);
            }
        });

        binding.activityRadioBack.setOnClickListener(view -> {
            Intent intent=new Intent(ElmoshafActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

}
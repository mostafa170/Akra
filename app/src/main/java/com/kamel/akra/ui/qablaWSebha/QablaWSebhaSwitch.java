package com.kamel.akra.ui.qablaWSebha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.kamel.akra.R;
import com.kamel.akra.base.BaseActivity;
import com.kamel.akra.databinding.ActivityQablaWSebhaSwitchBinding;
import com.kamel.akra.ui.Azkar.Azkar.AzkarFragment;
import com.kamel.akra.ui.Azkar.AzkarlListan.AzkarlListanFragment;
import com.kamel.akra.ui.ahadeth.AhadethActivity;
import com.kamel.akra.ui.home.MainActivity;
import com.kamel.akra.ui.qablaWSebha.qabla.QablaFragment;
import com.kamel.akra.ui.qablaWSebha.sebha.MentionFragment;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class QablaWSebhaSwitch extends BaseActivity {
    ActivityQablaWSebhaSwitchBinding binding;
    FragmentPagerItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qabla_w_sebha_switch);
        getSupportActionBar().hide();

        prepareTabsLayout();

        binding.activityRadioBack.setOnClickListener(view -> {
            Intent intent=new Intent(QablaWSebhaSwitch.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void prepareTabsLayout() {
        adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add(R.string.title_sebha, MentionFragment.class)
                        .add(R.string.title_qabla, QablaFragment.class)
                        .create());


        binding.viewPagerAuthentication.setAdapter(adapter);
        binding.viewpagertab.setViewPager(binding.viewPagerAuthentication);


        binding.viewPagerAuthentication.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                binding.viewPagerAuthentication.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }
}
package com.kamel.akra.ui.Azkar;

import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.kamel.akra.R;
import com.kamel.akra.base.BaseActivity;
import com.kamel.akra.databinding.ActivitySwitchBinding;
import com.kamel.akra.ui.Azkar.Azkar.AzkarFragment;
import com.kamel.akra.ui.Azkar.AzkarlListan.AzkarlListanFragment;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class SwitchActivity extends BaseActivity {

    ActivitySwitchBinding binding;
    FragmentPagerItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_switch);
        getSupportActionBar().hide();

        prepareTabsLayout();
    }

    public void prepareTabsLayout() {
        adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add(R.string.title_azkar, AzkarFragment.class)
                        .add(R.string.title_listan_azkar, AzkarlListanFragment.class)
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
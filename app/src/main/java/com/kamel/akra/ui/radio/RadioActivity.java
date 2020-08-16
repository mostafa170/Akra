package com.kamel.akra.ui.radio;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.kamel.akra.R;
import com.kamel.akra.base.BaseActivity;
import com.kamel.akra.databinding.ActivityRadioBinding;
import com.kamel.akra.network.RadioAPI;
import com.kamel.akra.ui.home.MainActivity;
import com.kamel.akra.ui.radio.adapter.RadioChannelsAdapter;
import com.kamel.akra.ui.radio.model.RadiosItem;
import com.kamel.akra.ui.radio.model.RadiosResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RadioActivity extends BaseActivity {

    RadioChannelsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ActivityRadioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_radio);
        getSupportActionBar().hide();

        adapter = new RadioChannelsAdapter(null);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.channelsRecyclerView.setAdapter(adapter);
        binding.channelsRecyclerView.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.channelsRecyclerView);

        binding.activityRadioBack.setOnClickListener(view -> {
            Intent intent=new Intent(RadioActivity.this, MainActivity.class);
            startActivity(intent);
        });

        adapter.setOnPlayClickListener(new RadioChannelsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, RadiosItem channel) {
                playRadio(channel.getURL());
            }
        });
        adapter.setOnStopClickListener(new RadioChannelsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, RadiosItem channel) {
                stopRadio();
            }
        });
        getRadioChannels();

    }

    MediaPlayer mediaPlayer;

    public void stopRadio(){
        if(mediaPlayer!=null)
            mediaPlayer.stop();
        Snackbar.with(RadioActivity.this,null)
                .type(Type.ERROR)
                .message("You click stop")
                .duration(Duration.LONG)
                .fillParent(true)
                .textAlign(Align.CENTER)
                .show();
    }

    public void playRadio(String URL){
        stopRadio();
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(URL);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();

                    Snackbar.with(RadioActivity.this,null)
                            .type(Type.SUCCESS)
                            .message("You click start")
                            .duration(Duration.LONG)
                            .fillParent(true)
                            .textAlign(Align.CENTER)
                            .show();
                }

            });
//            mediaPlayer.start();
        } catch (IOException e) {
            showMessage(R.string.error,R.string.cannot_play_channel);
        }




    }

    public void getRadioChannels(){
        showProgressBar(R.string.loading,R.string.please_wait);
        RadioAPI.getApis()
                .getRadioChannels()
                .enqueue(new Callback<RadiosResponse>() {
                    @Override
                    public void onResponse(Call<RadiosResponse> call,
                                           Response<RadiosResponse> response) {
                        hideProgressBar();
                        if(response.isSuccessful()){
                            adapter.changeData(response.body().getRadios());
                        }else {
                            //  response.code()
                            showMessage(R.string.error,R.string.cannot_connect_to_server);
                        }

                    }

                    @Override
                    public void onFailure(Call<RadiosResponse> call,
                                          Throwable t) {
                        hideProgressBar();
                        showMessage(getString(R.string.error),t.getLocalizedMessage());
                        // showMessage(R.string.error,R.string.cannot_connect_to_server);

                    }
                });


    }


}
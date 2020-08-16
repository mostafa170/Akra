package com.kamel.akra.ui.ListanElmoshaf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.kamel.akra.R;
import com.kamel.akra.databinding.ActivityListanQuranActivtyBinding;
import com.kamel.akra.ui.Azkar.AzkarlListan.ListanActivty;
import com.kamel.akra.ui.Azkar.SwitchActivity;

public class ListanQuranActivty extends AppCompatActivity {

    ActivityListanQuranActivtyBinding binding;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listan_quran_activty);
        getSupportActionBar().hide();

        mediaPlayer = new MediaPlayer();
        binding.playerSeekBar.setMax(100);

        binding.activityRadioBack.setOnClickListener(view -> {
            Intent intent=new Intent(ListanQuranActivty.this, ListanElmoshafActivity.class);
            startActivity(intent);
        });

        binding.fragLisTvNameLeader.setText("القارء/ "+getIntent().getStringExtra("nameLeader"));
        binding.fragLisTvNameSora.setText("السورة/ "+getIntent().getStringExtra("nameSora"));


        binding.imagePlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    handler.removeCallbacks(update);
                    mediaPlayer.pause();
                    binding.imagePlayPause.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                }else {
                    mediaPlayer.start();
                    binding.imagePlayPause.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                    upateSeekBar();
                }
            }
        });
        prepareMediaPlayer();

        binding.playerSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SeekBar seekBar=(SeekBar)view;
                int playPosition=(mediaPlayer.getDuration()/100)*seekBar.getProgress();
                mediaPlayer.seekTo(playPosition);
                binding.textCurrentTime.setText(milliSecondToTime((long) mediaPlayer.getCurrentPosition()));
                return false;
            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                binding.playerSeekBar.setSecondaryProgress(i);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                binding.playerSeekBar.setProgress(0);
                binding.imagePlayPause.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                binding.textCurrentTime.setText(R.string.zero);
                binding.textTotalDuration.setText(R.string.zero);
                mediaPlayer.reset();
                prepareMediaPlayer();
            }
        });
    }

    private void prepareMediaPlayer(){
        try {
            mediaPlayer.setDataSource(getIntent().getStringExtra("url"));
            mediaPlayer.prepare();
            binding.textTotalDuration.setText(milliSecondToTime((long) mediaPlayer.getDuration()));
        }catch (Exception e){
            Toast.makeText(this,"",Toast.LENGTH_LONG).show();
        }
    }

    private Runnable update = new Runnable() {
        @Override
        public void run() {
            upateSeekBar();
            long currentDuration=mediaPlayer.getCurrentPosition();
            binding.textCurrentTime.setText(milliSecondToTime(currentDuration));
        }
    };

    private void upateSeekBar() {
        if (mediaPlayer.isPlaying()) {
            binding.playerSeekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(update,1000);
        }
    }

    private String milliSecondToTime(Long milliSecond) {
        String timeString = "";
        String secondString;

        int hours = (int) (milliSecond / (1000 * 60 * 60));
        int minutes = (int) (milliSecond % (1000 * 60 * 60)) / (1000 * 60);
        int second = (int) ((milliSecond % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0) {
            timeString = hours + ":";
        }
        if (second < 10) {
            secondString = "0" + second;
        } else {
            secondString = "" + second;
        }
        timeString = timeString + minutes + ":" + secondString;
        return timeString;
    }
}
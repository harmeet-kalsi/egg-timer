package com.example.dell.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    TextView timerTextView;

    public void updateTimer(int secondsLeft){
        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        if(seconds<10) {
            timerTextView.setText(Integer.toString(minutes) + ":0" + seconds);
        }else{
            timerTextView.setText(Integer.toString(minutes) +":" + seconds);
        }
    }

    public void timerButton(View view){

        new CountDownTimer(timerSeekBar.getProgress() * 1000,1000){


            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mediaPlayer.start();
                Log.i("Finished", "Timer Stopped");
            }
        }.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar)findViewById(R.id.timerSeekbar);
        timerTextView = (TextView)findViewById(R.id.timerTextView);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);


        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int minutes = (int) progress / 60;
                int seconds = progress - minutes * 60;

                if(seconds<10) {
                    timerTextView.setText(Integer.toString(minutes) + ":0" + seconds);
                }else{
                    timerTextView.setText(Integer.toString(minutes) +":" + seconds);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

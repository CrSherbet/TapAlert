package com.example.waris.tapalert;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import model.Timer;

public class CountDownActivity extends AppCompatActivity {
    private Chronometer watch;
    private Button btn_cancel;
    private TextView status;
    private Timer timer = new Timer();
    private CountDownTimer counter;
    private long second = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        Intent intent = getIntent();
        String time = intent.getStringExtra("Time");

        status = (TextView) findViewById(R.id.status);
        watch = (Chronometer) findViewById(R.id.watcher);

        watch.setText(time);
        try {
            second = timer.convertToSecond(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        counter = new CountDownTimer(TimeUnit.SECONDS.toMillis(second),1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String text = timer.convertToString(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
                watch.setText(text);
            }

            @Override
            public void onFinish() {
                watch.setText("00:00:00");
                status.setText("Finished");
            }
        }.start();

    }
    public void cancel(View view){
        this.counter.cancel();
        status.setText("Cancel");
        finish();
    }
}

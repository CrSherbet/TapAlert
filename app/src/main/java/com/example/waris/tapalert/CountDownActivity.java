package com.example.waris.tapalert;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import model.Calculator;

public class CountDownActivity extends AppCompatActivity {
    private Chronometer watch;
    private TextView status;
    private Ringtone r;
    private Calculator timer = new Calculator();
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
                alert();
            }
        }.start();

    }

    public void alert(){
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Finished!!!");
        dialog.setCancelable(true);

       dialog.setPositiveButton("GET IT!",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        r.stop();
                        finish();
                    }
                }
       );

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

    }
    public void cancel(View view){
        this.counter.cancel();
        status.setText("Cancel");
        finish();
    }
}

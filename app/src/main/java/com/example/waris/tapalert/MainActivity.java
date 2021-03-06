package com.example.waris.tapalert;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity implements MainView{
    private MainPresenter presenter;
    private TextView textWidth;
    private TextView textVol;
    private Chronometer watch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        watch = (Chronometer) findViewById(R.id.textWatch);
        watch.setText("00:00:00");

        setUpWidthFn();
        setUpVolFn();
    }

    public void setupTime(View view){
        String txtTime = watch.getText().toString();
        Intent intent = new Intent(this, CountDownActivity.class);
        Log.d("Send",txtTime);
        intent.putExtra("Time", txtTime);
        startActivity(intent);

    }


    public void setUpWidthFn(){
        textWidth = (TextView) findViewById(R.id.textWidth);
        textWidth.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start,int before, int count) {
                if(s.length() != 0) {
                    presenter.calculateFlowRate(textWidth.getText().toString());
                }
            }
        });
    }

    public void setUpVolFn(){
        textVol = (TextView) findViewById(R.id.textVolume);
        textVol.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start,int before, int count) {
                if(s.length() != 0) {
                    presenter.calculateTime(textVol.getText().toString());
                }
            }
        });
    }

    public void editTime(View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(true);
        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setText(watch.getText().toString());

        dialog.setPositiveButton("Submit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        watch.setText(input.getText().toString());
                        try {
                            presenter.calculateVolume(watch.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        AlertDialog alertDialog = dialog.create();
        alertDialog.setView(input);
        alertDialog.show();
    }

    @Override
    public void showFlowRate(double flowRate) {
        TextView textFlowRate = (TextView) findViewById(R.id.textFlowRate);
        textFlowRate.setText(String.format("%.2f L/s",flowRate));
    }

    @Override
    public void showUsingTime(String time) {
        Chronometer watch = (Chronometer) findViewById(R.id.textWatch);
        watch.setText(time);
    }

    @Override
    public void showVolume(double vol) {
        textVol.setText(String.format("%.2f",vol));
    }
}

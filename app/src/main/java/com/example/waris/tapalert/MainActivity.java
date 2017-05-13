package com.example.waris.tapalert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView{
    private MainPresenter presenter;
    private TextView textWidth;
    private TextView textVol;
    private Button btn_start;
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

        btn_start = (Button) findViewById(R.id.btn_start);

    }

    public void setupTime(View view){
        String txtTime = watch.getText().toString();
        Intent intent = new Intent(this, CountDownActivity.class);
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
}

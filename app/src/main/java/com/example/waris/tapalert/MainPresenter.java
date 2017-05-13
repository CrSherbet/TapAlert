package com.example.waris.tapalert;

import android.util.Log;

import model.Tank;
import model.Tap;
import model.Timer;

/**
 * Created by waris on 5/12/2017.
 */

public class MainPresenter {
    private Tap tap;
    private Tank tank;
    private Timer timer;
    private MainView view;


    public MainPresenter(MainView view){
        this.view = view;
        this.tap = new Tap();
        this.tank = new Tank();
        this.timer = new Timer();
    }

    public void calculateFlowRate(String width){
        tap.setWidth(Double.parseDouble(width));
        double flowRate = tap.getFlowRate();
        view.showFlowRate(flowRate);
        calculateTime(tank.getVolume()+"");
    }

    public void calculateTime(String volume ){
        tank.setVolume(Double.parseDouble(volume));
        double usingTime = timer.calculateTime(tap.getFlowRate(), tank.getVolume());
        String time = timer.convertToString(usingTime);
        Log.d("Time",time+" "+usingTime);
        view.showUsingTime(time);
    }


}

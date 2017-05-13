package model;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by waris on 5/12/2017.
 */

public class Calculator {

    private Tap tap;
    private Tank tank;
    public Calculator(){
        this.tap = new Tap();
        this.tank = new Tank();
    }

    public double calculateTime(double flowRate, double volume){
        return volume/flowRate;
    }

    public double calculateVolume(double flowRate, long time){
        this.tank.setVolume(flowRate * time);
        return this.tank.getVolume();
    }

    public String convertToString(double second){
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String time = timeFormat.format(new  Date(TimeUnit.SECONDS.toMillis((long) second)));
        Log.d("2St", time + " " + second);
        return time;
    }

    public long convertToSecond(String time) throws ParseException {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = timeFormat.parse(time);
        Log.d("2s", time +" " + date.getTime() / 1000L);
        return date.getTime() / 1000L;
    }

    public void setWidth(String width) {
        this.tap.setWidth( Double.parseDouble(width));
    }

    public void setVolume(String volume) {
        this.tank.setVolume(Double.parseDouble(volume));
    }

    public double getVolume() {
        return this.tank.getVolume();
    }

    public double getFlowRate() {
        return this.tap.getFlowRate();
    }
}

package model;

import android.media.TimedText;
import android.provider.ContactsContract;
import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by waris on 5/12/2017.
 */

public class Timer {
    private double time;
    private double startTime;
    private double endTime;


    public Timer(){}

    public double calculateTime(double flowRate, double volume){
        return volume/flowRate;
    }

    public String convertToString(double second){
        Date date = new Date((long) second*1000);
        return new SimpleDateFormat("HH:mm.SSS").format(date);



    }

}

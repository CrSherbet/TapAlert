package model;

import android.media.TimedText;
import android.provider.ContactsContract;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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

}

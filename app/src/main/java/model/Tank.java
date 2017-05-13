package model;

/**
 * Created by waris on 5/12/2017.
 */

public class Tank {
    private double volume;

    public Tank(){
        setVolume(0);
    }

    public void setVolume(double vol){
        this.volume = vol;
    }

    public double getVolume(){
        return volume;
    }
}
package model;

/**
 * Created by waris on 5/12/2017.
 */

public class Tank {
    private double volume;

    public Tank(){}

    public void setVolume(Double vol){
        this.volume = vol * 0.001;
    }

    public double getVolume(){
        return volume;
    }

    public double convertToLiter(){
        return volume / 0.001;
    }
}
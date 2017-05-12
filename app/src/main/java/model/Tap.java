package model;

/**
 * Created by waris on 5/12/2017.
 */

public class Tap {
    private double width;
    private double area;
    private double flowRate;
    private final double speed = 0.8;

    public Tap (){}

    public void setWidth(double width){
        this.width = width * 0.0254;
        calculateArea();
        calculateFlowRate();
    }

    public void calculateArea(){
        this.area = Math.PI * Math.pow(width/2 ,2);
    }

    public void calculateFlowRate(){
        this.flowRate = speed * area;
    }

    public double getFlowRate(){
        return flowRate;
    }
}

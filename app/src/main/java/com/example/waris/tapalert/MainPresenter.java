package com.example.waris.tapalert;

import java.text.ParseException;

import model.Calculator;

/**
 * Created by waris on 5/12/2017.
 */

public class MainPresenter {

    private Calculator calculator;
    private MainView view;


    public MainPresenter(MainView view){
        this.view = view;
        this.calculator = new Calculator();
    }

    public void calculateFlowRate(String width){
        calculator.setWidth(width);
        double flowRate = calculator.getFlowRate();
        view.showFlowRate(flowRate);
        calculateTime(calculator.getVolume() + "");
    }

    public void calculateTime(String volume ){
        calculator.setVolume(volume);
        double usingTime = calculator.calculateTime(calculator.getFlowRate(), calculator.getVolume());
        String time = calculator.convertToString(usingTime);
        view.showUsingTime(time);
    }

    public void calculateVolume(String time) throws ParseException {
        long second = calculator.convertToSecond(time);
        calculator.calculateVolume(calculator.getFlowRate(),second);
        view.showVolume(calculator.getVolume());

    }

}

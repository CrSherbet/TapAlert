package com.example.waris.tapalert;

import org.junit.Test;

import model.Calculator;

import static org.junit.Assert.assertEquals;

/**
 * Created by waris on 5/14/2017.
 */

public class CalculatorUnitTest {

    Calculator cal;

    @Test
    public void setVolume_isCorrect() throws Exception {
        cal = new Calculator();
        cal.setVolume("0.4");
        assertEquals(0.4, cal.getVolume(),0);
    }

    @Test
    public void getFlowRate_isCorrect() throws Exception {
        cal = new Calculator();
        cal.setWidth("0.4");
        assertEquals(0.0324, cal.getFlowRate(),0.01);
    }

    @Test
    public void time_isCorrect() throws Exception {
        cal = new Calculator();
        cal.setWidth("1");
        cal.setVolume("2.5");
        assertEquals(12.35, cal.calculateTime(cal.getFlowRate(), cal.getVolume()),1);
    }

    @Test
    public void volume_isCorrect() throws Exception {
        cal = new Calculator();
        cal.setWidth("1.2");
        assertEquals(1.45, cal.calculateVolume(cal.getFlowRate(),5),1);
    }

    @Test
    public void convertTimeToString_isCorrect() throws Exception {
        cal = new Calculator();
        assertEquals("00:00:09", cal.convertToString(9));
    }
    @Test
    public void convertTimeInHour_isCorrect() throws Exception {
        cal = new Calculator();
        assertEquals("02:38:33", cal.convertToString(9513));
    }

    @Test
    public void convertStringToTime_isCorrect() throws Exception {
        cal = new Calculator();
        assertEquals(12, cal.convertToSecond("00:00:12"));
    }

    @Test
    public void convertStingInHour_isCorrect() throws Exception {
        cal = new Calculator();
        assertEquals(3912, cal.convertToSecond("01:05:12"));
    }

}

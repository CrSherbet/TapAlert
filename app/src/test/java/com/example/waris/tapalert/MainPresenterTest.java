package com.example.waris.tapalert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;

import static org.mockito.Mockito.verify;


/**
 * Created by waris on 5/14/2017.
 */

public class MainPresenterTest {
    private MainPresenter presenter;

    @Mock
    public MainView view;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(view);
    }

    @Test
    public void shouldShowFlowRate() {
        presenter.calculateFlowRate("0.3");
        verify(view).showFlowRate(0.018241469247509915d);
    }

    @Test
    public void shouldShowUsingTime() {
        presenter.calculateTime("2");
        verify(view).showUsingTime("07:12:55");
    }

    @Test
    public void shouldShowVolume() throws ParseException {
        presenter.calculateVolume("00:00:09");
        verify(view).showVolume(0.0d);
    }
}

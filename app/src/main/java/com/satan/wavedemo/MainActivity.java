package com.satan.wavedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FreqGainCurveView freqGainCurveView = (FreqGainCurveView) findViewById(R.id.freq_gain_curve);
        freqGainCurveView.setFreqGainEntries(FreqGainFactory.getTypicalEnties());
    }
}

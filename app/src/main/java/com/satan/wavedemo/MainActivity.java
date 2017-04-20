package com.satan.wavedemo;

import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hrules.charter.CharterLine;
import com.hrules.charter.CharterXLabels;
import com.hrules.charter.CharterYLabels;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CharterYLabels mYlableCharterYLabels;
    private CharterLine mLineCharterLine;
    private CharterXLabels mXlableCharterXLabels;
    private float[] valueX;
    private float[] valueY;
    private float[] valueLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLineCharterLine = (CharterLine) findViewById(R.id.charter_line);
        mXlableCharterXLabels = (CharterXLabels) findViewById(R.id.xlable);
        mYlableCharterYLabels = (CharterYLabels) findViewById(R.id.ylable);

        valueX = new float[]{35, 35*2,35*3,35*4,35*5,35*6,35*7,35*8,35*9,35*10,35*11,35*12};
        valueY = new float[]{-12, -8, -4, 0, 4, 8, 12};
        valueLine = new float[]{-10.5f, -8.3f, 0f, 1.2f, 2.23f, 5.3f, 4.2f, 3.3f, -5.4f};

        mXlableCharterXLabels.setValues(valueX);
        mYlableCharterYLabels.setValues(valueY);
        mLineCharterLine.setIndicatorVisible(false);
        mLineCharterLine.setValues(valueLine);
        mLineCharterLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueX = fillRandomValues(15,200,0);
                valueY = fillRandomValues(7,500,10);
                valueLine = fillRandomValues(15,500,10);
                mXlableCharterXLabels.setValues(valueX);
                mYlableCharterYLabels.setValues(valueY);
                mLineCharterLine.setValues(valueLine);
                mLineCharterLine.show();
            }
        });

        FreqGainCurveView freqGainCurveView = (FreqGainCurveView) findViewById(R.id.freq_gain_curve);
        freqGainCurveView.setFreqGainEntries(FreqGainFactory.getTypicalEnties());
    }

    private float[] fillRandomValues(int length, int max, int min) {
        Random random = new Random();
        float[] newRandomValues = new float[length];
        for (int i = 0; i < newRandomValues.length; i++) {
            newRandomValues[i] = random.nextInt(max - min + 1) - min;
        }
        return newRandomValues;
    }
}

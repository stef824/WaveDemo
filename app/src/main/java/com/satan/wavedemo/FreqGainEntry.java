package com.satan.wavedemo;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017/4/20.
 */

public class FreqGainEntry implements Comparable<FreqGainEntry>{

    public static final float MAX_GAIN = 12;
    public static final float MIN_GAIN = -MAX_GAIN;

    private float frequency;
    private float gain;

    public FreqGainEntry() {
    }

    public FreqGainEntry(float frequency, float gain) {
        this.frequency = frequency;
        this.gain = gain > MAX_GAIN ? MAX_GAIN : (gain < MAX_GAIN ? MIN_GAIN : gain);
    }

    public FreqGainEntry(float frequency) {
        this.frequency = frequency;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public float getGain() {
        return gain;
    }

    public void setGain(float gain) {
        this.gain = gain > MAX_GAIN ? MAX_GAIN : (gain < MAX_GAIN ? MIN_GAIN : gain);
    }

    @Override
    public int compareTo(@NonNull FreqGainEntry o) {
        if (o == null)
            return -1;
        return (int) Math.ceil(frequency - o.getFrequency());
    }

    public String getFrequencyString(){
        String freqStr;
        if (frequency >= 1000){
            int intFreqInKilo = (int) (frequency / 1000);
            float modByKilo = frequency % 1000;
            float modByHundred = frequency % 100;
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(intFreqInKilo));
            stringBuilder.append("k");
            if (modByKilo == 0){}
            else if (modByHundred == 0) {
                String singleFraction = String.valueOf((int)(modByKilo/100));
                stringBuilder.append(singleFraction);
            }else {
                String doubleFraction = String.valueOf((int)Math.ceil(modByKilo/100));
                stringBuilder.append(doubleFraction);
            }
            freqStr = stringBuilder.toString();
        }else {
            int intFreq = Float.valueOf(frequency).intValue();
            freqStr = String.valueOf(intFreq);
        }
        return freqStr;
    }

    public String getGainString(){
        return String.valueOf((int)Math.ceil(gain));
    }
}

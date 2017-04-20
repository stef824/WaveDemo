package com.satan.wavedemo;

import java.util.Collections;
import java.util.List;


/**
 * Created by Administrator on 2017/4/20.
 */

public class CoordinateHelper {
    public static float getRelativeX(FreqGainEntry freqGainEntry) {
         List<FreqGainEntry> freqGainEntries = FreqGainFactory.getTypicalEnties();

        if (freqGainEntries.size() < 2)
            throw new FreqGainException.TypicalFreqAmountException("Typical FreqGainEntry amount should be two at least");
        float relativeX = 0;
        boolean isTypical = false;
        for (int i = 0; i < freqGainEntries.size(); i++){
            FreqGainEntry entry = freqGainEntries.get(i);
            if (entry.getFrequency() == freqGainEntry.getFrequency()){
                relativeX = Float.valueOf(i) / (freqGainEntries.size() - 1);
                isTypical = true;
            }
        }

        if (isTypical) return relativeX;

        final int typicalEntryAmount = freqGainEntries.size();
        freqGainEntries.add(freqGainEntry);
        Collections.sort(freqGainEntries);
        for (int i = 0; i < freqGainEntries.size(); i++){
            FreqGainEntry entry = freqGainEntries.get(i);
            if (entry.getFrequency() == freqGainEntry.getFrequency()){
                if (i == 0){
                    float fraction = 1f / (typicalEntryAmount - 1) / (freqGainEntries.get(i + 2).getFrequency() - freqGainEntries.get(i + 1).getFrequency());
                    relativeX = fraction * (entry.getFrequency() - freqGainEntries.get(i + 1).getFrequency());
                }else if (i == freqGainEntries.size() - 1){
                    float fraction = 1f / (typicalEntryAmount - 1) / (freqGainEntries.get(i - 1).getFrequency() - freqGainEntries.get(i - 2).getFrequency());
                    relativeX = fraction * (entry.getFrequency() - freqGainEntries.get(i - 1).getFrequency()) + 1;
                }else {
                    float fraction = 1f / (typicalEntryAmount - 1) / (freqGainEntries.get(i + 1).getFrequency() - freqGainEntries.get(i - 1).getFrequency());
                    relativeX = fraction * (entry.getFrequency() - freqGainEntries.get(i - 1).getFrequency()) + i / (typicalEntryAmount - 1);
                }
            }
        }
        return relativeX;
    }

    public static float getRelativeY(FreqGainEntry freqGainEntry){
        float length = FreqGainEntry.MAX_GAIN - FreqGainEntry.MIN_GAIN;
        if (length <= 0)
            throw new FreqGainException.GainRangeException("Gain range should be larger than zero");
        return (FreqGainEntry.MAX_GAIN - freqGainEntry.getGain()) / length;
    }
}

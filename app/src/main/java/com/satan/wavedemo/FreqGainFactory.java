package com.satan.wavedemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */

public class FreqGainFactory {
    private static final float[] TYPICAL_FREQUENCIES = {25,
            31, 40, 50, 63, 80, 100, 125, 160, 200, 250, 315, 400,
            500, 630, 800, 1000, 1250, 1600, 2000, 2500, 3150, 4000,
            5000, 6300, 8000, 10000, 12500, 16000, 20000, 25000};
    public static List<FreqGainEntry> getTypicalEnties(){
        List<FreqGainEntry> entries = new ArrayList<>();
        for (float freq : TYPICAL_FREQUENCIES){
            entries.add(new FreqGainEntry(freq));
        }
        Collections.sort(entries);
        return entries;
    }
}

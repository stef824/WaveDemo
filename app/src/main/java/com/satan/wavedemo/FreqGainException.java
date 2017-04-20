package com.satan.wavedemo;

/**
 * Created by Administrator on 2017/4/20.
 */

public class FreqGainException {
    public static class TypicalFreqAmountException extends RuntimeException{
        public TypicalFreqAmountException(String message) {
            super(message);
        }
    }

    public static class GainRangeException extends RuntimeException{
        public GainRangeException(String message) {
            super(message);
        }
    }
}

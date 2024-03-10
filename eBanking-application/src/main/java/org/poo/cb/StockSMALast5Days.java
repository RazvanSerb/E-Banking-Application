package org.poo.cb;

public class StockSMALast5Days implements StockSMAStrategy {
    protected StockSMALast5Days() {}

    public double calculate(double[] values) {
        double SMAlast5days = 0;
        for (int i = 9; i >= 5; i--)
            SMAlast5days += values[i];
        SMAlast5days = SMAlast5days / 5.0;
        return SMAlast5days;
    }
}

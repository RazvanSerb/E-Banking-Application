package org.poo.cb;

public class StockSMALast10Days implements StockSMAStrategy {
    protected StockSMALast10Days() {}

    public double calculate(double[] values) {
        double SMAlast10days = 0;
        for (int i = 9; i >= 0; i--)
            SMAlast10days += values[i];
        SMAlast10days = SMAlast10days / 10.0;
        return SMAlast10days;
    }
}

package org.poo.cb;

public class StockSMA {
    private StockSMAStrategy strategy;
    protected StockSMA(StockSMAStrategy strategy) {
        this.strategy = strategy;
    }

    public double performCalculate(double[] values) {
        return strategy.calculate(values);
    }
}

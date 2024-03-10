package org.poo.cb;

public class ExchangeStrategyAmountOver50 implements ExchangeCurrencyStrategy {
    protected ExchangeStrategyAmountOver50() {}

    public double exchange(double amount, double exchangeRate) {
        return 1.01 * amount * exchangeRate;
    }
}

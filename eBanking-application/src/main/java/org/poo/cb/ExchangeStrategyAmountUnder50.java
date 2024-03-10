package org.poo.cb;

public class ExchangeStrategyAmountUnder50 implements ExchangeCurrencyStrategy {
    protected ExchangeStrategyAmountUnder50() {}

    public double exchange(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }
}

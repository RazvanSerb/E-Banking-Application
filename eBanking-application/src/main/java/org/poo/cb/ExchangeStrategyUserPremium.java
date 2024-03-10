package org.poo.cb;

public class ExchangeStrategyUserPremium implements ExchangeCurrencyStrategy {
    protected ExchangeStrategyUserPremium() {}

    public double exchange(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }
}

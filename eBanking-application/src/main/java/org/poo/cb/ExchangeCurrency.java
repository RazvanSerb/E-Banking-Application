package org.poo.cb;

public class ExchangeCurrency {
    private ExchangeCurrencyStrategy strategy;
    protected ExchangeCurrency(ExchangeCurrencyStrategy strategy) {
        this.strategy = strategy;
    }

    public double performExchange(double amount, double exchangeRate) {
        return strategy.exchange(amount, exchangeRate);
    }
}

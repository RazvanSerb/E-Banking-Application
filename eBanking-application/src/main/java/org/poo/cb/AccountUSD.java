package org.poo.cb;

public class AccountUSD extends Account {
    protected final String currency = "USD";
    protected double amount;
    protected AccountUSD(double amount) {
        this.amount = amount;
    }

    protected String getCurrency() {
        return currency;
    }
    protected void setAmount(double amount) {
        this.amount = amount;
    }
    protected double getAmount() {
        return amount;
    }
}

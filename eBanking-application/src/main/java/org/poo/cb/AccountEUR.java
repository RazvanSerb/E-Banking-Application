package org.poo.cb;

public class AccountEUR extends Account {
    protected final String currency = "EUR";
    protected double amount;
    protected AccountEUR(double amount) {
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

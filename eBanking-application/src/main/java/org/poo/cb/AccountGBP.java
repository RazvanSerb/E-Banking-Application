package org.poo.cb;

public class AccountGBP extends Account {
    protected final String currency = "GBP";
    protected double amount;
    protected AccountGBP(double amount) {
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

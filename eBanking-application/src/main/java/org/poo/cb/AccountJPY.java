package org.poo.cb;

public class AccountJPY extends Account {
    protected final String currency = "JPY";
    protected double amount;
    protected AccountJPY(double amount) {
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

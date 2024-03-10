package org.poo.cb;

public class AccountCAD extends Account {
    protected final String currency = "CAD";
    protected double amount;
    protected AccountCAD(double amount) {
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

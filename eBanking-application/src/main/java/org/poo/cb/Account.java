package org.poo.cb;

public abstract class Account {
    protected abstract String getCurrency();
    protected abstract void setAmount(double amount);
    protected abstract double getAmount();
}

package org.poo.cb;

public class Stock {
    protected String company;
    protected double[] values = new double[10];
    protected double currentValue;
    protected boolean recommended;
    protected Stock(String company, double[] values) {
        this.company = company;
        System.arraycopy(values, 0, this.values, 0, 10);
        this.currentValue = values[9];
        this.recommended = false;
    }
}

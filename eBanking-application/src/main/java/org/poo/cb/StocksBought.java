package org.poo.cb;

public class StocksBought extends Stock {
    protected int noOfStocks;
    public StocksBought(String company, double[] values, int noOfStocks) {
        super(company, values);
        this.noOfStocks = noOfStocks;
    }
}

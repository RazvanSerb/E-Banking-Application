package org.poo.cb;

public class ErrorMessageInsufficientAmountBuyingPremium extends ErrorTemplate {
    protected ErrorMessageInsufficientAmountBuyingPremium() {}

    protected String createBody(String info) {
        return "Insufficient amount in account for buying premium option";
    }
}

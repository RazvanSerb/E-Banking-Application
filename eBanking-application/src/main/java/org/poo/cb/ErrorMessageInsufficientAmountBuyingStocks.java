package org.poo.cb;

public class ErrorMessageInsufficientAmountBuyingStocks extends ErrorTemplate {
    protected ErrorMessageInsufficientAmountBuyingStocks() {}

    protected String createBody(String info) {
        return "Insufficient amount in account for buying stock";
    }
}

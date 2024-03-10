package org.poo.cb;

public class ErrorMessageInsufficientAmountExchange extends ErrorTemplate {
    protected ErrorMessageInsufficientAmountExchange() {}

    protected String createBody(String info) {
        return "Insufficient amount in account " + info + " for exchange";
    }
}

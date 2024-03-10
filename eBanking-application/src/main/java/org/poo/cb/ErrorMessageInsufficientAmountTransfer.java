package org.poo.cb;

public class ErrorMessageInsufficientAmountTransfer extends ErrorTemplate {
    protected ErrorMessageInsufficientAmountTransfer() {}

    protected String createBody(String info) {
        return "Insufficient amount in account " + info + " for transfer";
    }
}

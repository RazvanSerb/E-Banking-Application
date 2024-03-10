package org.poo.cb;

import java.util.*;

public class CommandExchangeMoney extends Command {
    protected CommandExchangeMoney() {}

    protected void execute(Bank bank, List<String> commandParameters) {
        String email = commandParameters.get(0);
        String sourceCurrency = commandParameters.get(1);
        String destinationCurrency = commandParameters.get(2);
        double amount = Double.parseDouble(commandParameters.get(3));
        try {
            bank.exchangeMoney(email, sourceCurrency, destinationCurrency, amount);
        } catch (ExceptionInsufficientAmount e) {
            ErrorTemplate message = new ErrorMessageInsufficientAmountExchange();
            message.printMessage(sourceCurrency);
        }
    }
}
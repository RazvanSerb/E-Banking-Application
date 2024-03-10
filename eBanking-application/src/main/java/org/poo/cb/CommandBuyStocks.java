package org.poo.cb;

import java.util.*;

public class CommandBuyStocks extends Command {
    protected CommandBuyStocks() {}

    protected void execute(Bank bank, List<String> commandParameters) {
        String email = commandParameters.get(0);
        String company = commandParameters.get(1);
        int noOfStocks = Integer.parseInt(commandParameters.get(2));
        try {
            bank.buyStocks(email, company, noOfStocks);
        } catch (ExceptionInsufficientAmount e) {
            ErrorTemplate message = new ErrorMessageInsufficientAmountBuyingStocks();
            message.printMessage(null);
        }
    }
}

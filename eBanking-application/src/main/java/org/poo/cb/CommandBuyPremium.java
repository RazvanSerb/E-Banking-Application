package org.poo.cb;

import java.util.*;

public class CommandBuyPremium extends Command {
    protected CommandBuyPremium() {}

    protected void execute(Bank bank, List<String> commandParameters) {
        String email = commandParameters.get(0);
        try {
            bank.buyPremium(email);
        } catch (ExceptionUserNonExistent e1) {
            ErrorTemplate message = new ErrorMessageUserNonExistent();
            message.printMessage(email);
        } catch (ExceptionInsufficientAmount e2) {
            ErrorTemplate message = new ErrorMessageInsufficientAmountBuyingPremium();
            message.printMessage(null);
        }
    }
}

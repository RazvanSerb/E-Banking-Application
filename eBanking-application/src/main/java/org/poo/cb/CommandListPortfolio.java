package org.poo.cb;

import java.util.*;

public class CommandListPortfolio extends Command {
    protected CommandListPortfolio() {}

    protected void execute(Bank bank, List<String> commandParameters) {
        String email = commandParameters.get(0);
        try {
            bank.listPortfolio(email);
        } catch (ExceptionUserNonExistent e) {
            ErrorTemplate message = new ErrorMessageUserNonExistent();
            message.printMessage(email);
        }
    }
}

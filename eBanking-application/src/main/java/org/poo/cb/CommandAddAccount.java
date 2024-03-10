package org.poo.cb;

import java.util.*;

public class CommandAddAccount extends Command {
    protected CommandAddAccount() {}

    protected void execute(Bank bank, List<String> commandParameters) {
        String email = commandParameters.get(0);
        String currency = commandParameters.get(1);
        try {
            bank.addAccount(email, currency);
        } catch (ExceptionAccountDuplicated e) {
            ErrorTemplate message = new ErrorMessageAccountDuplicated();
            message.printMessage(currency);
        }
    }
}
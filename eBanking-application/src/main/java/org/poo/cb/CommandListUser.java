package org.poo.cb;

import java.util.*;

public class CommandListUser extends Command {
    protected CommandListUser() {}

    protected void execute(Bank bank, List<String> commandParameters) {
        String email = commandParameters.get(0);
        try {
            bank.listUser(email);
        } catch (ExceptionUserNonExistent e) {
            ErrorTemplate message = new ErrorMessageUserNonExistent();
            message.printMessage(email);
        }
    }
}

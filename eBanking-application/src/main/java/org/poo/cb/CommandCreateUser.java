package org.poo.cb;

import java.util.*;

public class CommandCreateUser extends Command {
    protected CommandCreateUser() {}

    protected void execute(Bank bank, List<String> commandParameters) {
        String email = commandParameters.get(0);
        String firstname = commandParameters.get(1);
        String lastname = commandParameters.get(2);
        String address = "";
        int cnt = 3;
        for (String tmp : commandParameters.subList(3, commandParameters.size())) {
            address += tmp;
            cnt++;
            if (cnt != commandParameters.size())
                address += " ";
        }
        try {
            bank.createUser(email, firstname, lastname, address);
        } catch (ExceptionUserDuplicated e) {
            ErrorTemplate message = new ErrorMessageUserDuplicated();
            message.printMessage(email);
        }
    }
}

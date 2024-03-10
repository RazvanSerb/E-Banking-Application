package org.poo.cb;

import java.util.*;

public class CommandAddMoney extends Command {
    protected CommandAddMoney() {}

    protected void execute(Bank bank, List<String> commandParameters) {
        String email = commandParameters.get(0);
        String currency = commandParameters.get(1);
        double amount = Double.parseDouble(commandParameters.get(2));
        bank.addMoney(email, currency, amount);
    }
}

package org.poo.cb;

import java.util.*;

public class CommandTransferMoney extends Command {
    protected CommandTransferMoney() {}

    protected void execute(Bank bank, List<String> commandParameters) {
        String emailUser = commandParameters.get(0);
        String emailFriend = commandParameters.get(1);
        String currency = commandParameters.get(2);
        double amount = Double.parseDouble(commandParameters.get(3));
        try {
            bank.transferMoney(emailUser, emailFriend, currency, amount);
        } catch (ExceptionInsufficientAmount e1) {
            ErrorTemplate message = new ErrorMessageInsufficientAmountTransfer();
            message.printMessage(currency);
        } catch (ExceptionUserFriendNot e2) {
            ErrorTemplate message = new ErrorMessageUserFriendNot();
            message.printMessage(emailFriend);
        }
    }
}

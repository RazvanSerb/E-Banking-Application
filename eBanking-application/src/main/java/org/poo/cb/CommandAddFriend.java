package org.poo.cb;

import java.util.*;

public class CommandAddFriend extends Command {
    protected CommandAddFriend() {}

    protected void execute(Bank bank, List<String> commandParameters) {
        String emailUser = commandParameters.get(0);
        String emailFriend = commandParameters.get(1);
        try {
            bank.addFriend(emailUser, emailFriend);
        } catch (ExceptionUserNonExistent e1) {
            ErrorTemplate message = new ErrorMessageUserNonExistent();
            if (!bank.getInstance().users.containsKey(emailUser))
                message.printMessage(emailUser);
            else
                message.printMessage(emailFriend);
        } catch (ExceptionUserFriendAlready e2) {
            ErrorTemplate message = new ErrorMessageUserFriendAlready();
            message.printMessage(emailFriend);
        }
    }
}

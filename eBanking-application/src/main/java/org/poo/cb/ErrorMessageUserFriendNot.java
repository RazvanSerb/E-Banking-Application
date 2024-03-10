package org.poo.cb;

public class ErrorMessageUserFriendNot extends ErrorTemplate {
    protected ErrorMessageUserFriendNot() {}

    protected String createBody(String info) {
        return "You are not allowed to transfer money to " + info;
    }
}

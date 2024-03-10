package org.poo.cb;

public class ErrorMessageUserFriendAlready extends ErrorTemplate {
    protected ErrorMessageUserFriendAlready() {}

    protected String createBody(String info) {
        return "User with " + info + " is already a friend";
    }
}

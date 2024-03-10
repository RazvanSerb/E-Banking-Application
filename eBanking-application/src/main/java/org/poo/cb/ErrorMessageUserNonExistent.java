package org.poo.cb;

public class ErrorMessageUserNonExistent extends ErrorTemplate {
    protected ErrorMessageUserNonExistent() {}

    protected String createBody(String info) {
        return "User with " + info + " doesn't exist";
    }
}

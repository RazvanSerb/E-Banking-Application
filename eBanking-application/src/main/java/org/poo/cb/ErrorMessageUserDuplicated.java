package org.poo.cb;

public class ErrorMessageUserDuplicated extends ErrorTemplate {
    protected ErrorMessageUserDuplicated() {}

    protected String createBody(String info) {
        return "User with " + info + " already exists";
    }
}

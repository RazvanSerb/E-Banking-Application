package org.poo.cb;

public class ErrorMessageAccountDuplicated extends ErrorTemplate {
    protected ErrorMessageAccountDuplicated() {}

    protected String createBody(String info) {
        return "Account in currency " + info + " already exists for user";
    }
}

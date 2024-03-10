package org.poo.cb;

public abstract class ErrorTemplate {
    protected final void printMessage(String info) {
        String body = createBody(info);
        send(body);
    }
    protected abstract String createBody(String info);
    private void send(String body) {
        System.out.println(body);
    }
}

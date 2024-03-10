package org.poo.cb;

public class AccountBaseFactory {
    protected AccountBaseFactory() {}

    protected Account createAccount(String currency) {
        switch (currency) {
            case "USD":
                return new AccountUSD(0);
            case "EUR":
                return new AccountEUR(0);
            case "GBP":
                return new AccountGBP(0);
            case "JPY":
                return new AccountJPY(0);
            case "CAD":
                return new AccountCAD(0);
            default:
                return null;
        }
    }
}

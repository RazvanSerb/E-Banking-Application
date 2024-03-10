package org.poo.cb;

import java.io.*;
import java.util.*;

public class Bank {
    private static Bank instance;
    protected File fileExchangeRates;
    protected TreeMap<String, Stock> stocks;
    protected LinkedHashMap<String, User> users;
    private Bank() {
        this.fileExchangeRates = null;
        this.stocks = new TreeMap<>();
        this.users = new LinkedHashMap<>();
    }

    protected void setInstanceNull() {
        instance = null;
    }
    protected static Bank getInstance() {
        if (instance == null)
            instance = new Bank();
        return instance;
    }
    protected void createUser(String email, String firstname, String lastname, String address) throws ExceptionUserDuplicated {
        if (users.containsKey(email))
            throw new ExceptionUserDuplicated();
        User user = new User.Builder().withEmail(email).withFirstname(firstname).withLastname(lastname).withAddress(address).build();
        users.put(email, user);
    }
    protected void addFriend(String emailUser, String emailFriend) throws ExceptionUserNonExistent, ExceptionUserFriendAlready {
        if (!users.containsKey(emailUser) || !users.containsKey(emailFriend))
            throw new ExceptionUserNonExistent();
        User user = users.get(emailUser);
        User friend = users.get(emailFriend);
        if (user.friends.containsKey(emailFriend))
            throw new ExceptionUserFriendAlready();
        user.friends.put(emailFriend, friend);
        friend.friends.put(emailUser, user);
    }
    protected void addAccount(String email, String currency) throws ExceptionAccountDuplicated {
        User user = users.get(email);
        if (user.accounts.containsKey(currency))
            throw new ExceptionAccountDuplicated();
        Account account = new AccountBaseFactory().createAccount(currency);
        user.accounts.put(currency, account);
    }
    protected void addMoney(String email, String currency, double amount) {
        User user = users.get(email);
        Account account = user.accounts.get(currency);
        account.setAmount(account.getAmount() + amount);
    }
    protected void exchangeMoney(String email, String sourceCurrency, String destinationCurrency, double amount) throws ExceptionInsufficientAmount {
        User user = users.get(email);
        Account sourceAccount = user.accounts.get(sourceCurrency);
        Account destinationAccount = user.accounts.get(destinationCurrency);
        double exchangeRate = 0;
        Scanner scanner;
        try {
            scanner = new Scanner(fileExchangeRates);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> buffer = new ArrayList<>();
        if (scanner.hasNextLine())
            buffer.addAll(List.of(scanner.nextLine().split(",")));
        int sourcePosition = 0;
        for (String currency : buffer) {
            if (currency.equals(sourceCurrency))
                break;
            sourcePosition++;
        }
        int destinationPosition = 0;
        for (String currency : buffer) {
            if (currency.equals(destinationCurrency))
                break;
            destinationPosition++;
        }
        buffer.clear();
        int cntLines = 0;
        while (scanner.hasNextLine()) {
            int cntColumns = 0;
            buffer.addAll(List.of(scanner.nextLine().split(",")));
            if (cntLines == destinationPosition) {
                for (String tmp : buffer) {
                    if (cntColumns == sourcePosition) {
                        exchangeRate = Double.parseDouble(tmp);
                        break;
                    }
                    cntColumns++;
                }
                break;
            }
            cntLines++;
        }
        if (sourceAccount.getAmount() < amount * exchangeRate)
            throw new ExceptionInsufficientAmount();
        if (user.premium) {
            ExchangeCurrency exchangePremium = new ExchangeCurrency(new ExchangeStrategyUserPremium());
            sourceAccount.setAmount(sourceAccount.getAmount() - exchangePremium.performExchange(amount, exchangeRate));
        } else if (sourceAccount.getAmount() > 2 * amount * exchangeRate) {
            ExchangeCurrency exchangeUnder50 = new ExchangeCurrency(new ExchangeStrategyAmountUnder50());
            sourceAccount.setAmount(sourceAccount.getAmount() - exchangeUnder50.performExchange(amount, exchangeRate));
        } else {
            ExchangeCurrency exchangeOver50 = new ExchangeCurrency(new ExchangeStrategyAmountOver50());
            sourceAccount.setAmount(sourceAccount.getAmount() - exchangeOver50.performExchange(amount, exchangeRate));
        }
        destinationAccount.setAmount(destinationAccount.getAmount() + amount);
    }
    protected void transferMoney(String emailUser, String emailFriend, String currency, double amount) throws ExceptionInsufficientAmount, ExceptionUserFriendNot {
        User user = users.get(emailUser);
        Account sourceAccount = user.accounts.get(currency);
        User friend = users.get(emailFriend);
        Account destinationAccount = friend.accounts.get(currency);
        if (sourceAccount.getAmount() < amount)
            throw new ExceptionInsufficientAmount();
        if (!user.friends.containsKey(emailFriend))
            throw new ExceptionUserFriendNot();
        sourceAccount.setAmount(sourceAccount.getAmount() - amount);
        destinationAccount.setAmount(destinationAccount.getAmount() + amount);
    }
    protected void buyStocks(String email, String company, int noOfStocks) throws ExceptionInsufficientAmount {
        User user = users.get(email);
        Account account = user.accounts.get("USD");
        Stock stock = stocks.get(company);
        if (account.getAmount() < noOfStocks * stock.currentValue)
            throw new ExceptionInsufficientAmount();
        StocksBought stocksBought = new StocksBought(stock.company, stock.values, noOfStocks);
        if (user.premium && stock.recommended)
            account.setAmount(account.getAmount() - 0.95 * noOfStocks * stock.currentValue);
        else
            account.setAmount(account.getAmount() - noOfStocks * stock.currentValue);
        user.stocks.put(company, stocksBought);
    }
    protected void recommendStocks() {
        System.out.print("{" + '"' + "stocksToBuy" + '"' + ":" + "[");
        boolean ok = false;
        for (String company : stocks.keySet()) {
            Stock stock = stocks.get(company);
            stock.recommended = false;
            StockSMA stockLast5Days = new StockSMA(new StockSMALast5Days());
            double SMAlast5days = stockLast5Days.performCalculate(stock.values);
            StockSMA stockLast10Days = new StockSMA(new StockSMALast10Days());
            double SMAlast10days = stockLast10Days.performCalculate(stock.values);
            if (SMAlast5days > SMAlast10days) {
                if (ok)
                    System.out.print(",");
                stock.recommended = true;
                System.out.print('"' + company + '"');
                ok = true;
            }
        }
        System.out.print("]" + "}");
        System.out.println();
    }
    protected void listUser(String email) throws ExceptionUserNonExistent {
        if (!users.containsKey(email))
            throw new ExceptionUserNonExistent();
        User user = users.get(email);
        System.out.print("{" + '"' + "email" + '"' + ":" + '"' + email + '"' + ",");
        System.out.print('"' + "firstname" + '"' + ":" + '"' + user.getFirstname() + '"' + ",");
        System.out.print('"' + "lastname" + '"' + ":" + '"' + user.getLastname() + '"' + ",");
        System.out.print('"' + "address" + '"' + ":" + '"' + user.getAddress() + '"' + ",");
        System.out.print('"' + "friends" + '"' + ":" + "[");
        int cnt = 0;
        for (String emailFriend : user.friends.keySet()) {
            System.out.print('"' + emailFriend + '"');
            cnt++;
            if (cnt != user.friends.keySet().size())
                System.out.print(",");
        }
        System.out.print("]" + "}");
        System.out.println();
    }
    protected void listPortfolio(String email) throws ExceptionUserNonExistent {
        if (!users.containsKey(email))
            throw new ExceptionUserNonExistent();
        User user = users.get(email);
        System.out.print("{" + '"' + "stocks" + '"' + ":" + "[");
        int cnt = 0;
        for (String company : user.stocks.keySet()) {
            System.out.print("{" + '"' + "stockName" + '"' + ":" + '"' + company + '"' + ",");
            System.out.print('"' + "amount" + '"' + ":" + user.stocks.get(company).noOfStocks + "}");
            cnt++;
            if (cnt != user.stocks.keySet().size())
                System.out.print(",");
        }
        System.out.print("]");
        cnt = 0;
        System.out.print("," + '"' + "accounts" + '"' + ":" + "[");
        for (String currency : user.accounts.keySet()) {
            System.out.print("{" + '"' + "currencyName" + '"' + ":" + '"' + currency + '"' + ",");
            System.out.print('"' + "amount" + '"' + ":" + '"' + String.format(Locale.US, "%.2f", user.accounts.get(currency).getAmount()) + '"' + "}");
            cnt++;
            if (cnt != user.accounts.size())
                System.out.print(",");
        }
        System.out.print("]");
        System.out.print("}");
        System.out.println();
    }
    protected void buyPremium(String email) throws ExceptionUserNonExistent, ExceptionInsufficientAmount {
        if (!users.containsKey(email))
            throw new ExceptionUserNonExistent();
        User user = users.get(email);
        Account account = user.accounts.get("USD");
        if (account.getAmount() < 100)
            throw new ExceptionInsufficientAmount();
        account.setAmount(account.getAmount() - 100);
        user.premium = true;
    }
}

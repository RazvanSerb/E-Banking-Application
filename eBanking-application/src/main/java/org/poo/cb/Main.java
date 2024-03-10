package org.poo.cb;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Running Main");
            return;
        }
        Bank bank = Bank.getInstance();
        bank.fileExchangeRates = new File("src/main/resources/" + args[0]);
        File fileStocks = new File("src/main/resources/" + args[1]);
        Scanner scanner;
        try {
            scanner = new Scanner(fileStocks);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (scanner.hasNextLine())
            scanner.nextLine();
        while (scanner.hasNextLine()) {
            ArrayList<String> buffer = new ArrayList<>(List.of(scanner.nextLine().split(",")));
            String company = buffer.get(0);
            int cnt = 0;
            double[] values = new double[10];
            for (String tmp : buffer.subList(1, buffer.size()))
                values[cnt++] = Double.parseDouble(tmp);
            Stock stock = new Stock(company, values);
            bank.stocks.put(company, stock);
        }
        Queue<String> commandsHistory = new LinkedList<>();
        File fileIn = new File("src/main/resources/" + args[2]);
        try {
            scanner = new Scanner(fileIn);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine())
            commandsHistory.offer(scanner.nextLine());
        while (!commandsHistory.isEmpty()) {
            String command = commandsHistory.poll();
            ArrayList<String> commandParameters = new ArrayList<>(List.of(command.split(" ")));
            String commandVerb = commandParameters.get(0) + " " + commandParameters.get(1);
            switch (commandVerb) {
                case "CREATE USER":
                    new CommandCreateUser().execute(bank, commandParameters.subList(2, commandParameters.size()));
                    break;
                case "ADD FRIEND":
                    new CommandAddFriend().execute(bank, commandParameters.subList(2, commandParameters.size()));
                    break;
                case "ADD ACCOUNT":
                    new CommandAddAccount().execute(bank, commandParameters.subList(2, commandParameters.size()));
                    break;
                case "ADD MONEY":
                    new CommandAddMoney().execute(bank, commandParameters.subList(2, commandParameters.size()));
                    break;
                case "EXCHANGE MONEY":
                    new CommandExchangeMoney().execute(bank, commandParameters.subList(2, commandParameters.size()));
                    break;
                case "TRANSFER MONEY":
                    new CommandTransferMoney().execute(bank, commandParameters.subList(2, commandParameters.size()));
                    break;
                case "BUY STOCKS":
                    new CommandBuyStocks().execute(bank, commandParameters.subList(2, commandParameters.size()));
                    break;
                case "RECOMMEND STOCKS":
                    new CommandRecommendStocks().execute(bank, null);
                    break;
                case "LIST USER":
                    new CommandListUser().execute(bank, commandParameters.subList(2, commandParameters.size()));
                    break;
                case "LIST PORTFOLIO":
                    new CommandListPortfolio().execute(bank, commandParameters.subList(2, commandParameters.size()));
                    break;
                case "BUY PREMIUM":
                    new CommandBuyPremium().execute(bank, commandParameters.subList(2, commandParameters.size()));
                    break;
                default:
                    return;
            }
        }
        bank.setInstanceNull();
    }
}
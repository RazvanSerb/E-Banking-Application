package org.poo.cb;

import java.util.*;

public class CommandRecommendStocks extends Command {
    protected CommandRecommendStocks() {}

    protected void execute(Bank bank, List<String> commandParameters) {
        bank.recommendStocks();
    }
}

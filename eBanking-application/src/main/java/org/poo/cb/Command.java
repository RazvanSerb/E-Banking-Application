package org.poo.cb;

import java.util.*;

public abstract class Command {
    protected abstract void execute(Bank bank, List<String> commandParameters);
}

package ru.tinkoff.edu.java.chain;

import ru.tinkoff.edu.java.parsers.BaseParser;
import ru.tinkoff.edu.java.parsers.GitHubParser;
import ru.tinkoff.edu.java.parsers.Parser;
import ru.tinkoff.edu.java.parsers.StackOverflowParser;

public class ParseChain {
    public static Parser chain() {
        return chain(new GitHubParser(), new StackOverflowParser());
    }

    public static Parser chain(BaseParser firstParser, BaseParser... parsers) {
        BaseParser previous = firstParser;
        for (BaseParser current : parsers) {
            previous.setSuccessor(current);
            previous = current;
        }
        return firstParser;
    }
}

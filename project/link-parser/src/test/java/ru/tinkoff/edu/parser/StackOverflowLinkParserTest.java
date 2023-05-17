package ru.tinkoff.edu.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.record.StackOverflowRecord;

public class StackOverflowLinkParserTest {
    private final StackOverflowLinkParser stackOverflowLinkParser = new StackOverflowLinkParser(null);

    @Test
    public void invalidLinkParse() {
        String link = "https://stackoverflow.com/search?q=unsupported%20link";
        Record record = stackOverflowLinkParser.parseLink(link);
        Assertions.assertNull(record);
    }
}

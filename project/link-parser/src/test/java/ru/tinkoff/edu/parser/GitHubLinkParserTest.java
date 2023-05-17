package ru.tinkoff.edu.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.record.GitHubRecord;

public class GitHubLinkParserTest {
    private final GitHubLinkParser gitHubLinkParser = new GitHubLinkParser(null);
    private final String username = "spkdakrll";

    @Test
    public void invalidLinkParse() {
        String link1 = "https://github.com/" + username;
        Record record1 = gitHubLinkParser.parseLink(link1);
        Assertions.assertNull(record1);
    }
}

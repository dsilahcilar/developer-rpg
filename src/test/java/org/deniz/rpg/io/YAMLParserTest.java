package org.deniz.rpg.io;

import org.deniz.rpg.model.Fields;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class YAMLParserTest {

    private YAMLParser parser = new YAMLParser();

    private final String cardText = "experience: 3\n" +
            "satisfaction: 2\n" +
            "money: -5\n" +
            "self:\n" +
            "  title: You need to learn jsf...\n" +
            "  description: bla bla bla";

    private final String selfText = "self:\n" +
            "  title: Backend navigation\n" +
            "  description: As a backend developer you need to learn these topics, please chose one of them.";

    private final String listText = "condition:\n" +
            "  cards:\n" +
            "    - java\n" +
            "    - spring";

    private final String singleListText = "self:\n" +
            "  title: You need to learn jsf...\n" +
            "  description: bla bla bla\n" +
            "condition:\n" +
            "  cards:\n" +
            "    - java\n" +
            "experience: 3\n" +
            "satisfaction: 2\n" +
            "money: -5";

    @Test
    public void shouldParseSimpleProperties() {
        String experience = parser.parse(cardText, Fields.EXPERIENCE.getName());
        String satisfaction = parser.parse(cardText, Fields.SATISFACTION.getName());
        String money = parser.parse(cardText, Fields.MONEY.getName());

        assertThat(experience, is("3"));
        assertThat(satisfaction, is("2"));
        assertThat(money, is("-5"));
    }

    @Test
    public void shouldParseEmbeddedProperties() {
        String selfTitle = parser.parse(cardText, Fields.SELF_TITLE.getName());
        String description = parser.parse(cardText, Fields.SELF_DESCRIPTION.getName());

        assertThat(selfTitle, is("You need to learn jsf..."));
        assertThat(description, is("bla bla bla"));
    }

    @Test
    public void shouldParseSelfFile() {
        String selfTitle = parser.parse(selfText, Fields.SELF_TITLE.getName());
        String description = parser.parse(selfText, Fields.SELF_DESCRIPTION.getName());

        assertThat(selfTitle, is("Backend navigation"));
        assertThat(description, is("As a backend developer you need to learn these topics, please chose one of them."));
    }

    @Test
    public void shouldParseList() {
        List<String> cards = parser.parseList(listText, Fields.CONDITION_CARDS.getName());

        assertThat(cards, Matchers.containsInAnyOrder("java", "spring"));
    }

    @Test
    public void shouldParseSingleList() {
        List<String> cards = parser.parseList(singleListText, Fields.CONDITION_CARDS.getName());

        assertThat(cards, Matchers.containsInAnyOrder("java"));
    }

}
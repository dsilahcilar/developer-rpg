package org.deniz.rpg.builder;

import org.deniz.rpg.model.Card;
import org.deniz.rpg.model.Page;
import org.deniz.rpg.model.Self;

import java.util.HashMap;
import java.util.Map;

public class BePages {

    public static Page bePage() {
        Self self = new Self("Backend developer",
                "As a backend developer you need to learn these topics, please chose one of them.");

        Map<String, Page> childPages = new HashMap<>();
        childPages.put("db", dbPage());
        childPages.put("lang", langPage());

        return Page.builder()
                .self(self)
                .childPages(childPages)
                .build();
    }

    private static Page langPage() {
        Self self = new Self("Choose your main language",
                "As a backend developer you need to choose one of these languages");
        Map<String, Page> childPages = new HashMap<>();
        childPages.put("java", javaPage());
        childPages.put("python", pythonPage());

        return Page.builder()
                .self(self)
                .childPages(childPages)
                .build();
    }

    private static Page pythonPage() {
        Self self = new Self("As a python developer you need learn...",
                "As a python developer you need to choose one of these topics");

        Map<String, Card> cards = new HashMap<>();
        Card python = Card.builder()
                .self(new Self("You need to learn python...", "python python python"))
                .experience(3).satisfaction(2)
                .build();
        Card django = Card.builder()
                .self(new Self("You need to learn django...", "django django django"))
                .experience(3).satisfaction(2)
                .build();

        cards.put("python", python);
        cards.put("django", django);

        return Page.builder()
                .self(self)
                .cards(cards)
                .build();
    }

    private static Page javaPage() {
        Self self = new Self("As a java developer you need learn...",
                "As a java developer you need to choose one of these topics");

        Map<String, Card> cards = new HashMap<>();
        Card java = Card.builder()
                .self(new Self("You need to learn java...", "java java java"))
                .experience(3).satisfaction(2)
                .build();
        Card jsf = Card.builder()
                .self(new Self("You need to learn jsf...", "bla bla bla"))
                .experience(3).satisfaction(2)
                .build();
        Card spring = Card.builder()
                .self(new Self("You need to learn spring...", "spring"))
                .experience(3).satisfaction(2)
                .build();

        cards.put("java", java);
        cards.put("jsf", jsf);
        cards.put("spring", spring);

        return Page.builder()
                .self(self)
                .cards(cards)
                .build();
    }

    private static Page dbPage() {
        Self self = new Self("Choose your main database",
                "As a backend developer you need to learn one of these databases");

        Map<String, Card> cards = new HashMap<>();
        Card sql = Card.builder()
                .self(new Self("You need to learn sql...", "select * from"))
                .experience(3).satisfaction(2)
                .build();
        cards.put("sql", sql);

        return Page.builder()
                .self(self)
                .cards(cards)
                .build();
    }

}

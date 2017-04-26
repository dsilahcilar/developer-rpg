package org.deniz.rpg.builder;

import org.deniz.rpg.model.Card;
import org.deniz.rpg.model.Page;
import org.deniz.rpg.model.Self;

import java.util.HashMap;
import java.util.Map;

public class FePages {

    public static Page fePage() {
        Self self = new Self("FrontEnd developer",
                "As a FrontEnd developer you need to learn these topics, please chose one of them.");

        Map<String, Page> childPages = new HashMap<>();
        childPages.put("basics", basicsPage());
        childPages.put("frameworks", frameworksPage());

        return Page.builder()
                .self(self)
                .childPages(childPages)
                .build();
    }

    private static Page frameworksPage() {
        Self self = new Self("Fe frameworks",
                "Fe frameworks description");
        Map<String, Card> cards = new HashMap<>();
        Card angular = Card.builder()
                .self(new Self("You need to learn angular...", "angular angular angular"))
                .experience(3).satisfaction(2)
                .build();
        cards.put("angular", angular);

        return Page.builder()
                .self(self)
                .cards(cards)
                .build();
    }

    private static Page basicsPage() {
        Self self = new Self("Fe basics title",
                "Fe basics description");
        Map<String, Card> cards = new HashMap<>();
        Card html = Card.builder()
                .self(new Self("You need to learn html...", "html html html"))
                .experience(3).satisfaction(2)
                .build();
        cards.put("html", html);

        return Page.builder()
                .self(self)
                .cards(cards)
                .build();
    }


}

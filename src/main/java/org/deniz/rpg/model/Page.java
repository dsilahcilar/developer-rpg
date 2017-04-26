package org.deniz.rpg.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
public class Page implements Selectable {
    private Self self;
    private Map<String, Card> cards;
    private Map<String, Page> childPages;

    public Optional<Page> toPage(String pageName) {
        Page page = childPages.get(pageName);
        return Optional.ofNullable(page);
    }

    public static PageBuilder builder() {
        return new PageBuilder();
    }

    private Page(Self self, Map<String, Card> cards, Map<String, Page> childPages) {
        this.self = self;
        this.cards = cards;
        this.childPages = childPages;
    }

    public static class PageBuilder {
        private Self self;
        private Map<String, Card> cards = Collections.emptyMap();
        private Map<String, Page> childPages = Collections.emptyMap();

        public PageBuilder self(Self self) {
            this.self = self;
            return this;
        }

        public PageBuilder cards(Map<String, Card> cards) {
            if (cards != null) {
                this.cards = cards;
            }
            return this;
        }

        public PageBuilder childPages(Map<String, Page> childPages) {
            if (childPages != null) {
                this.childPages = childPages;
            }
            return this;
        }

        public Page build() {
            return new Page(this.self, this.cards, this.childPages);
        }
    }
}

package org.deniz.rpg.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Card implements Selectable {
    private Self self;
    private Condition condition;
    private Integer experience;
    private Integer satisfaction;

    private Card(Self self, Condition condition, Integer experience, Integer satisfaction) {
        this.self = self;
        this.condition = condition;
        this.experience = experience;
        this.satisfaction = satisfaction;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(self, card.self);
    }

    @Override
    public int hashCode() {
        return Objects.hash(self);
    }

    public static CardBuilder builder() {
        return new CardBuilder();
    }

    public static class CardBuilder {
        private Self self;
        private Condition condition;
        private Integer experience;
        private Integer satisfaction;

        public CardBuilder self(Self self) {
            this.self = self;
            return this;
        }

        public CardBuilder condition(Condition condition) {
            this.condition = condition;
            return this;
        }

        public CardBuilder experience(Integer experience) {
            this.experience = experience;
            return this;
        }

        public CardBuilder satisfaction(Integer satisfaction) {
            this.satisfaction = satisfaction;
            return this;
        }

        public Card build() {
            return new Card(this.self, this.condition, this.experience, this.satisfaction);
        }

    }

}

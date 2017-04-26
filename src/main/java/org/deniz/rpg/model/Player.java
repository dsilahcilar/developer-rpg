package org.deniz.rpg.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Player {
    private String name;
    private Integer experience;
    private Integer satisfaction;
    private Integer money;
    private Integer age;
    private Map<String, Card> cards = new HashMap<>();
    private HiddenSkills hiddenSkills;

    public Integer addSatisfaction(Integer satisfaction) {
        this.satisfaction += satisfaction;
        return this.satisfaction;
    }

    public Integer addTime(Integer time) {
        this.age += time;
        return this.age;
    }

    public Integer substractMoney(Integer money) {
        this.money -= money;
        return money;
    }

    public void addCard(String key, Card card) {
        this.cards.put(key, card);
    }

    public boolean hasCard(String cardName) {
        return this.cards.get(cardName) != null;
    }

    public void addExperience(Integer experience) {
        this.experience += experience;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                ", satisfaction=" + satisfaction +
                ", money=" + money +
                ", age=" + age +
                ", cards=" + cards +
                '}';
    }

}

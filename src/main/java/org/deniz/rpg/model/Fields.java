package org.deniz.rpg.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Fields {
    CONDITION_CARDS("condition.cards"),
    CONDITION_EXPERIENCE("condition.experience"),
    SELF_TITLE("self.title"),
    SELF_DESCRIPTION("self.description"),
    EXPERIENCE("experience"),
    SATISFACTION("satisfaction"),
    MONEY("money");

    private final String name;
}

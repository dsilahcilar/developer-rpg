package org.deniz.rpg.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Condition {
    private List<String> cards;
    private Integer experience;
}

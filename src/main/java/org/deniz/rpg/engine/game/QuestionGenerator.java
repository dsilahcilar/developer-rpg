package org.deniz.rpg.engine.game;

import org.deniz.rpg.model.Page;
import org.deniz.rpg.model.Selectable;

import java.util.Map;

public interface QuestionGenerator {
    Map<String, Selectable> generateQuestions(Page page);
}

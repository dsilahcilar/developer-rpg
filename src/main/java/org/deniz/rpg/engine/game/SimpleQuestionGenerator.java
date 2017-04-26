package org.deniz.rpg.engine.game;

import org.deniz.rpg.engine.game.QuestionGenerator;
import org.deniz.rpg.model.Page;
import org.deniz.rpg.model.Selectable;

import java.util.HashMap;
import java.util.Map;

public class SimpleQuestionGenerator implements QuestionGenerator {

    public Map<String, Selectable> generateQuestions(Page page) {
        Map<String, Selectable> questionMap = new HashMap<>();
        for (String key : page.getCards().keySet()) {
            questionMap.put(key, page.getCards().get(key));
        }
        Map<String, Page> childPages = page.getChildPages();
        for (String key : childPages.keySet()) {
            questionMap.put(key, childPages.get(key));
        }
        return questionMap;
    }

}

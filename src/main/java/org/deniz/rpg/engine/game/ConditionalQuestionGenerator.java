package org.deniz.rpg.engine.game;

import lombok.RequiredArgsConstructor;
import org.deniz.rpg.model.Card;
import org.deniz.rpg.model.Page;
import org.deniz.rpg.model.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ConditionalQuestionGenerator implements QuestionGenerator {
    private final QuestionGenerator simpleQuestionGenerator;
    private final PlayerService playerService;

    public Map<String, Selectable> generateQuestions(Page page) {
        Map<String, Selectable> questions = simpleQuestionGenerator.generateQuestions(page);
        List<String> removedQuestionKeys = new ArrayList<>();
        for (Map.Entry<String, Selectable> eachEntry : questions.entrySet()) {
            if (playerService.hasCard(eachEntry.getKey())) {
                removedQuestionKeys.add(eachEntry.getKey());
            }
            if (eachEntry.getValue() instanceof Card) {
                Card card = (Card) eachEntry.getValue();
                if (card.getCondition() != null) {
                    for (String cardName : card.getCondition().getCards()) {
                        if (!playerService.hasCard(cardName)) {
                            removedQuestionKeys.add(eachEntry.getKey());
                        }
                    }
                }
            }
        }
        for (String removedQuestionKey : removedQuestionKeys) {
            questions.remove(removedQuestionKey);
        }
        return questions;
    }

}

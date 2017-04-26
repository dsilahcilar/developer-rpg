package org.deniz.rpg.engine.game;

import lombok.RequiredArgsConstructor;
import org.deniz.rpg.engine.game.QuestionGenerator;
import org.deniz.rpg.io.IOHelper;
import org.deniz.rpg.io.InputService;
import org.deniz.rpg.model.Page;
import org.deniz.rpg.model.Selectable;
import org.deniz.rpg.model.Self;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class QuestionService {
    private final QuestionGenerator questionGenerator;
    private final InputService inputService;
    private final IOHelper io;
    private Map<String, Selectable> questions = new HashMap<>();

    private String askQuestion(Map<String, Selectable> questions) {
        Map<Integer, String> qMap = new HashMap<>();
        Integer counter = 1;
        for (Map.Entry<String, Selectable> eachQuestion : questions.entrySet()) {
            final Self self = eachQuestion.getValue().getSelf();
            io.writeAndSleep(counter + ") " + self.getTitle() + " : " + self.getDescription());
            qMap.put(counter++, eachQuestion.getKey());
        }
        int input = inputService.readInt("Please select one of these numbers: ");
        return qMap.get(input);
    }

    public Selectable getSelection(String selectionKey) {
        return questions.get(selectionKey);
    }

    public String askQuestions(Page page) {
        questions = questionGenerator.generateQuestions(page);
        if(questions.isEmpty()) {
            return null;
        }
        return askQuestion(questions);
    }


}

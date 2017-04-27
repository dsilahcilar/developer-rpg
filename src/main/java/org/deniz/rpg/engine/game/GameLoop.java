package org.deniz.rpg.engine.game;

import lombok.RequiredArgsConstructor;
import org.deniz.rpg.model.Card;
import org.deniz.rpg.model.Page;
import org.deniz.rpg.model.Selectable;

@RequiredArgsConstructor
public class GameLoop {
    private final LearningService learningService;
    private final QuestionService questionService;
    private final PlayerService playerService;

    private void loop(Page page) {
        String selectionKey = questionService.askQuestions(page);
        if(selectionKey == null) {
            return;
        }
        Selectable selection = questionService.getSelection(selectionKey);
        if (selection instanceof Page) {
            loop(page.toPage(selectionKey).get());
            loop(page);
        } else if (selection instanceof Card) {
            Card card = (Card) selection;
            learningService.learn(card, playerService.getPlayer());
            playerService.getPlayer().addCard(selectionKey, card);
            playerService.getPlayer().addExperience(card.getExperience());
            loop(page);
        }
    }

    public void run() {
        PageGenerator pageGenerator = new PageGenerator();
        String rootPath = System.getenv("CAR_DIR");
        if(rootPath == null) {
            rootPath = getClass().getClassLoader().getResource("cards").getPath();
        }
        Page page = pageGenerator.buildTree(rootPath);
        playerService.createPlayer();
        loop(page);
    }

}

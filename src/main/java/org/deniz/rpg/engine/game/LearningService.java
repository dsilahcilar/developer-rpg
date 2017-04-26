package org.deniz.rpg.engine.game;

import lombok.RequiredArgsConstructor;
import org.deniz.rpg.engine.game.EducationFactory;
import org.deniz.rpg.io.IOHelper;
import org.deniz.rpg.io.InputService;
import org.deniz.rpg.model.Card;
import org.deniz.rpg.model.Education;
import org.deniz.rpg.model.Player;

@RequiredArgsConstructor
public class LearningService {
    private static final int DEFAULT_LOST_SATISFACTION_VALUE = -3;
    private final InputService inputService;
    private final IOHelper io;
    private final EducationFactory educationFactory;


    public void learn(Card card, Player player) {
        learn(card, player, 0);
    }

    public void learn(Card card, Player player, Integer earnedExperience) {
        io.writeAndSleep("player = " + player.toString());
        io.writeAndSleep("You have chosen:  " + card.getSelf().getTitle() + " : " + card.getSelf().getDescription());
        io.writeAndSleep("Select your learning method: ");
        io.writeAndSleep("1) Book: ");
        io.writeAndSleep("2) Online: ");
        io.writeAndSleep("3) Self: ");
        int selection = inputService.readInt("Select your learning method: ");

        Education education = educationFactory.getEducation(selection);
        earnedExperience += education.experience();
        player.addTime(education.time());
        player.substractMoney(education.price());

        io.write("Are you ready for " + card.getSelf().getTitle() + " exam ? ");
        selection = askForQuiz();

        if (selection == 1) {
            if (earnedExperience > card.getExperience()) {
                io.writeAndSleep("Congratulations you pass the exam!");
                return;
            } else {
                io.writeAndSleep("Unfortunately you couldn't pass the exam!", 300);
                io.writeAndSleep("Don't give up, try again!", 300);
                player.addSatisfaction(DEFAULT_LOST_SATISFACTION_VALUE);
            }
        }
        learn(card, player, earnedExperience);
    }

    private int askForQuiz() {
        int selection = inputService.readInt("Press 1 for Quiz, or Press 2 for to continue learning: ");
        if (selection == 1 || selection == 2) {
            return selection;
        }
        return askForQuiz();
    }

}

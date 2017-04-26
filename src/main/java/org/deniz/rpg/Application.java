package org.deniz.rpg;

import org.deniz.rpg.configuration.AppConfiguration;
import org.deniz.rpg.engine.game.GameLoop;
import org.deniz.rpg.engine.game.RuleValidator;

public class Application {

    public static void main(String[] args) {
        RuleValidator ruleValidator = new RuleValidator();
        ruleValidator.checkSelfFiles();
        AppConfiguration appConfiguration = new AppConfiguration();
        GameLoop gameLoop = appConfiguration.gameLoop();
        System.out.println(" ==================================================================");
        System.out.println("You are a software developer at the beginning of your career.\n" +
                " There is a long way ahead to reach your dream, living in The Bahamas.\n " +
                "You want to save money enough to buy a house and for living costs.\n " +
                "After you bought house you are not going to work, you will live your dream life. \n" +
                "You will be at the beach all day, take a sunbathe, drink exotic cocktails etc.\n" +
                " You need to save 500.000$ to buy a house in The Bahamas and another 100.000$ for \n" +
                "living costs. You should improve yourself and to be successful at your career to achieve \n" +
                "your goals. You have 1000 weeks in front of you. If your satisfaction decreases to 0 you \n" +
                "will lose.  To increase your satisfaction you can buy holiday or shopping cards.\n" +
                " Random events like job offers and promotions increase your satisfaction. \n");
        System.out.println("Good luck!");
        System.out.println(" ==================================================================");
        System.out.println(" ");
        boolean isSaved = appConfiguration.storageService().isAnySavedGame();
        if(isSaved) {
            int response = appConfiguration.terminalInputService().readInt("You have a saved game press 0 to start a new game or any number to continue with saved game ");
            if(response == 0) {
                appConfiguration.storageService().delete();
            }
        }
        gameLoop.run();
    }


}

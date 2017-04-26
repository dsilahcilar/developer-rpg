package org.deniz.rpg.engine.game;

import org.deniz.rpg.io.IOHelper;
import org.deniz.rpg.io.InputService;
import org.deniz.rpg.model.HiddenSkills;
import org.deniz.rpg.model.Player;

public class PlayerService {
    private final IOHelper io;
    private final InputService inputService;
    private Player player;
    private static PlayerService self;

    private PlayerService(IOHelper io, InputService inputService) {
        this.io = io;
        this.inputService = inputService;
    }

    public static PlayerService getInstance(IOHelper io, InputService inputService) {
        if(self == null) {
            self = new PlayerService(io,inputService);
        }
        return self;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Player createPlayer() {
        String name = inputService.readStr("Write your user name: ");
        player = new Player();
        player.setName(name);
        player.setSatisfaction(100);
        player.setExperience(0);
        player.setAge(1);
        player.setMoney(100);
        HiddenSkills hiddenSkills = readHiddenSkills();
        player.setHiddenSkills(hiddenSkills);
        return player;
    }

    private HiddenSkills readHiddenSkills() {
        io.writeAndSleep("You need to set your side skills: ", 200);
        io.writeAndSleep("1) Social Intelligence ", 200);
        io.writeAndSleep("2) Logical Intelligence ", 200);
        io.writeAndSleep("3) Creativity ", 200);
        io.writeAndSleep("4) Hardworking ", 200);
        io.writeAndSleep("These skills effect you to complete your tasks", 200);
        io.writeAndSleep("Give a number between 1 and 9 for each skills", 200);
        io.writeAndSleep("Sum of 4 should be 25", 200);


        int socialIntelligence = inputService.readInt("Social Intelligence: ");
        int logicalIntelligence = inputService.readInt("Logical Intelligence: ");
        int creativity = inputService.readInt("Creativity : ");
        int hardWorking = inputService.readInt("Hardworking : ");
        int sum = socialIntelligence + logicalIntelligence + creativity + hardWorking;
        if (sum != 25) {
            return readHiddenSkills();
        }
        return new HiddenSkills(socialIntelligence, logicalIntelligence, creativity, hardWorking);
    }

    public boolean hasCard(String key) {
        return getPlayer().hasCard(key);
    }
}

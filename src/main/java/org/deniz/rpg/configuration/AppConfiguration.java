package org.deniz.rpg.configuration;

import org.deniz.rpg.engine.game.*;
import org.deniz.rpg.engine.storage.DiskStorageService;
import org.deniz.rpg.engine.storage.DiskWriter;
import org.deniz.rpg.engine.storage.StorageConverter;
import org.deniz.rpg.engine.storage.StorageService;
import org.deniz.rpg.io.IOHelper;
import org.deniz.rpg.io.InputProxy;
import org.deniz.rpg.io.InputService;
import org.deniz.rpg.io.TerminalInputService;

public class AppConfiguration {

    public TerminalInputService terminalInputService() {
        return new TerminalInputService();
    }

    public QuestionGenerator questionGenerator() {
        return new ConditionalQuestionGenerator(simpleQuestionGenerator(), playerService());
    }

    public QuestionGenerator simpleQuestionGenerator() {
        return new SimpleQuestionGenerator();
    }

    public LearningService learningService() {
        return new LearningService(inputService(), ioHelper(), educationFactory());
    }

    public EducationFactory educationFactory() {
        return new EducationFactory();
    }

    public IOHelper ioHelper() {
        return IOHelper.getInstance();
    }

    public StorageService storageService() {
        return DiskStorageService.getInstance(new StorageConverter());
    }

    public InputService inputService() {
        InputProxy inputService = InputProxy.getInstance(terminalInputService(), storageService());
        inputService.addListener(DiskWriter.getInstance());
        return inputService;
    }

    public GameLoop gameLoop() {
        return new GameLoop(learningService(), questionService(), playerService());
    }

    private QuestionService questionService() {
        return new QuestionService(questionGenerator(), inputService(), ioHelper());
    }

    private PlayerService playerService() {
        return PlayerService.getInstance(ioHelper(), inputService());
    }

}

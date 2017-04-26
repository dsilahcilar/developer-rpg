package org.deniz.rpg.engine.game;

import org.deniz.rpg.io.FileHelper;
import org.deniz.rpg.io.YAMLParser;
import org.deniz.rpg.model.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageGenerator {
    public static final String SELF_FILE = "self.yml";
    private FileHelper reader = new FileHelper();
    private YAMLParser parser = new YAMLParser();


    public Page buildTree(final String rootPath) {
        Self self = readSelf(rootPath);

        return Page.builder()
                .self(self)
                .childPages(readChildPages(rootPath))
                .build();
    }

    private Map<String, Page> readChildPages(final String path) {
        List<String> paths = reader.paths(path);
        Map<String, Page> pageMap = new HashMap<>();
        for (String eachPath : paths) {
            final String directory = path + File.separator + eachPath;
            Self self = readSelf(directory);
            Map<String, Card> cards = readCards(directory);
            Page page = Page.builder()
                    .self(self)
                    .cards(cards)
                    .childPages(readChildPages(directory))
                    .build();
            pageMap.put(eachPath, page);
        }
        return pageMap;
    }

    private Map<String, Card> readCards(final String path) {
        List<File> files = reader.files(path);
        Map<String, Card> cards = new HashMap<>();
        for (File eachFile : files) {
            if (!eachFile.getPath().contains(SELF_FILE)) {
                String text = reader.read(eachFile.getAbsolutePath());
                Card card = parseCard(text);
                cards.put(eachFile.getName().replace(".yml",""), card);
            }
        }
        return cards;
    }

    private Card parseCard(final String text) {
        Integer satisfaction = parser.parseInt(text, Fields.SATISFACTION.getName());
        Integer experience = parser.parseInt(text, Fields.EXPERIENCE.getName());
        Self self = parseSelf(text);
        return Card.builder()
                .self(self)
                .condition(condition(text))
                .experience(experience)
                .satisfaction(satisfaction)
                .build();
    }

    private Condition condition(final String text) {
        List<String> cards = parser.parseList(text, Fields.CONDITION_CARDS.getName());
        Integer conditionExperience = parser.parseInt(text, Fields.CONDITION_EXPERIENCE.getName());
        if (cards.isEmpty() && conditionExperience == null) {
            return null;
        }
        return new Condition(cards, conditionExperience);
    }

    private Self readSelf(final String path) {
        String text = reader.read(path + File.separator + SELF_FILE);
        return parseSelf(text);
    }

    private Self parseSelf(final String text) {
        String title = parser.parse(text, Fields.SELF_TITLE.getName());
        String description = parser.parse(text, Fields.SELF_DESCRIPTION.getName());
        return new Self(title, description);
    }

}

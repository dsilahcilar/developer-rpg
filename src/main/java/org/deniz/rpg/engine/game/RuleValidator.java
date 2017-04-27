package org.deniz.rpg.engine.game;

import org.deniz.rpg.io.FileHelper;

import java.io.File;
import java.util.List;

public class RuleValidator {
    private FileHelper fileHelper = new FileHelper();

    public void checkSelfFiles() {
        String rootPath = System.getenv("CAR_DIR");
        if(rootPath == null) {
            rootPath = getClass().getClassLoader().getResource("cards").getPath();
        }
        check(rootPath);
    }

    private void check(String path) {
        if(!fileHelper.isExists(path + File.separator + PageGenerator.SELF_FILE)) {
            throw new RuntimeException("Every directory should have one self file! " + path);
        }
        List<String> paths = fileHelper.paths(path);
        for (String eachPath : paths) {
            check(path + File.separator + eachPath);
        }
    }
}

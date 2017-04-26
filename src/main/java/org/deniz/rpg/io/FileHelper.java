package org.deniz.rpg.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHelper {

    public String read(String filePath) {
        File file = getFile(filePath);
        StringBuilder content = new StringBuilder();
        try {
            List<String> linesStr = Files.readAllLines(file.toPath());
            for (String line : linesStr) {
                content.append(line).append(System.lineSeparator());
            }
            content.deleteCharAt(content.length() - 1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return content.toString();
    }

    public List<String> paths(String path) {
        File file = getFile(path);
        List<String> paths = new ArrayList<>();
        File[] directories = file.listFiles(File::isDirectory);
        for (File directory : directories) {
            paths.add(directory.getName());
        }
        return paths;
    }

    public List<File> files(String path) {
        return Arrays.asList(getFile(path).listFiles(File::isFile));
    }

    public Boolean isExists(String filePath) {
        return getFile(filePath).exists();
    }

    private File getFile(String path) {
        return new File(path);
    }
}

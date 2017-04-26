package org.deniz.rpg.engine.storage;

import org.deniz.rpg.engine.game.Listener;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiskWriter implements Listener {
    private static DiskWriter self;

    private DiskWriter() {

    }

    public static DiskWriter getInstance() {
        if (self == null) {
            self = new DiskWriter();
        }
        return self;
    }

    private void writeToDisk(Map<Integer, Integer> integerInputs, Map<Integer, String> stringInputs) {
        List<String> inputs = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> eachEntry : integerInputs.entrySet()) {
            sb.append(eachEntry.getKey())
                    .append(":")
                    .append(eachEntry.getValue())
                    .append(",");
        }
        inputs.add(sb.toString());
        sb = new StringBuilder();
        for (Map.Entry<Integer, String> eachEntry : stringInputs.entrySet()) {
            sb.append(eachEntry.getKey())
                    .append(":")
                    .append(eachEntry.getValue())
                    .append(",");
        }
        inputs.add(sb.toString());
        try {
            URL path = getClass().getClassLoader().getResource(StorageEnum.STORAGE_PATH.getName());
            Files.write(Paths.get(path.getPath()), inputs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listen(Map<Integer, Integer> integerInputs, Map<Integer, String> stringInputs) {
        writeToDisk(integerInputs, stringInputs);
    }
}

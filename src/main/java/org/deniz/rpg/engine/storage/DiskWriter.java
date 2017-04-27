package org.deniz.rpg.engine.storage;

import org.deniz.rpg.engine.game.Listener;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class DiskWriter implements Listener {
    private static DiskWriter self;
    private StorageConverter storageConverter = new StorageConverter();

    private DiskWriter() {

    }

    public static DiskWriter getInstance() {
        if (self == null) {
            self = new DiskWriter();
        }
        return self;
    }

    private void writeToDisk(Map<Integer, Integer> integerInputs, Map<Integer, String> stringInputs) {
        List<String> inputs = storageConverter.toList(integerInputs, stringInputs);
        try {
            String storagePath = System.getenv("STORAGE_PATH");
            if (storagePath == null) {
                URL path = getClass().getClassLoader().getResource(StorageEnum.STORAGE_PATH.getName());
                storagePath = path.getPath();
            }
            Files.write(Paths.get(storagePath), inputs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listen(Map<Integer, Integer> integerInputs, Map<Integer, String> stringInputs) {
        writeToDisk(integerInputs, stringInputs);
    }
}

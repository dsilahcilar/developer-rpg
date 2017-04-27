package org.deniz.rpg.engine.storage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiskStorageService implements StorageService {
    private final StorageConverter storageConverter;
    private List<String> state;
    private Map<Integer, Integer> integerMap;
    private Map<Integer, String> stringMap;

    private static DiskStorageService self;

    private DiskStorageService(StorageConverter storageConverter) {
        this.storageConverter = storageConverter;
    }

    public static DiskStorageService getInstance(StorageConverter storageConverter) {
        if (self == null) {
            self = new DiskStorageService(storageConverter);
        }
        return self;
    }

    @Override
    public Integer getInput(Integer key) {
        if (integerMap == null) {
            integerMap = storageConverter.toMap(getState());
        }
        return integerMap.get(key);
    }

    @Override
    public String getInputStr(Integer key) {
        if (stringMap == null) {
            stringMap = storageConverter.toStringMap(getState());
        }
        return stringMap.get(key);
    }

    @Override
    public boolean isAnySavedGame() {
        return !getState().isEmpty();
    }

    @Override
    public void delete() {
        state = new ArrayList<>();
    }


    private List<String> getState() {
        if (state == null) {
            state = new ArrayList<>();
            String storagePath = System.getenv("STORAGE_PATH");
            if(storagePath == null) {
                URL path = getClass().getClassLoader().getResource(StorageEnum.STORAGE_PATH.getName());
                storagePath = path.getPath();
            }
            try {
                state = Files.readAllLines(Paths.get(storagePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return state;
    }
}

package org.deniz.rpg.engine.storage;

public interface StorageService {
    Integer getInput(Integer key);

    String getInputStr(Integer key);

    boolean isAnySavedGame();

    void delete();
}

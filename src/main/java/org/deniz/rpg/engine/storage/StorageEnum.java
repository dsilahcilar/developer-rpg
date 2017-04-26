package org.deniz.rpg.engine.storage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum  StorageEnum {
    STORAGE_PATH("storage/state.txt"),
    KEY_VALUE_SPLITTER(":"),
    LINE_SPLITTER(",");

    private final String name;

}

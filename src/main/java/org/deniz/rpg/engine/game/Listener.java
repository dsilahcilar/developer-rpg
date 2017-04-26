package org.deniz.rpg.engine.game;

import java.util.Map;

public interface Listener {
    void listen(Map<Integer, Integer> integerInputs, Map<Integer, String> stringInputs);
}

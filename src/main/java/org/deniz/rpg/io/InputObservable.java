package org.deniz.rpg.io;

import org.deniz.rpg.engine.game.Listener;

import java.util.Map;

public interface InputObservable {
    void addListener(Listener listener);
    void notifyListeners(Map<Integer, Integer> integerInputs, Map<Integer, String> stringInputs);
}

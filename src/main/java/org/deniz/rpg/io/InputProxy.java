package org.deniz.rpg.io;

import org.deniz.rpg.engine.game.Listener;
import org.deniz.rpg.engine.storage.StorageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InputProxy implements InputService, InputObservable {
    private static InputProxy self;
    private final InputService inputService;
    private final Map<Integer, Integer> integerMap = new HashMap<>();
    private final Map<Integer, String> stringMap = new HashMap<>();
    private final StorageService storageService;
    private List<Listener> listeners = new ArrayList<>();
    private Integer intCounter = 0;
    private Integer stringCounter = 0;

    public InputProxy(InputService inputService, StorageService storageService) {
        this.inputService = inputService;
        this.storageService = storageService;
    }

    public static InputProxy getInstance(InputService inputService, StorageService storageService) {
        if (self == null) {
            self = new InputProxy(inputService, storageService);
        }
        return self;
    }

    public int readInt(String text) {
        Integer input = storageService.getInput(intCounter);
        if (input == null) {
            input = inputService.readInt(text);
        }
        integerMap.put(intCounter++, input);
        notifyListeners(integerMap, stringMap);
        return input;
    }

    public String readStr(String text) {
        String input = storageService.getInputStr(stringCounter);
        if (input == null) {
            input = inputService.readStr(text);
        }
        stringMap.put(stringCounter++, input);
        notifyListeners(integerMap, stringMap);
        return input;
    }

    @Override
    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void notifyListeners(Map<Integer, Integer> integerInputs, Map<Integer, String> stringInputs) {
        for (Listener listener : this.listeners) {
            listener.listen(integerInputs, stringInputs);
        }
    }
}

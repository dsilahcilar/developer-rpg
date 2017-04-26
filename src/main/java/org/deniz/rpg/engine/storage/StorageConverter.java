package org.deniz.rpg.engine.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageConverter {

    public List<String> toList(Map<Integer, Integer> intMap, Map<Integer, String> stringMap) {
        List<String> inputs = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> eachEntry : intMap.entrySet()) {
            sb.append(eachEntry.getKey())
                    .append(StorageEnum.KEY_VALUE_SPLITTER.getName())
                    .append(eachEntry.getValue())
                    .append(",");
        }
        inputs.add(sb.toString());
        sb = new StringBuilder();
        for (Map.Entry<Integer, String> eachEntry : stringMap.entrySet()) {
            sb.append(eachEntry.getKey())
                    .append(StorageEnum.KEY_VALUE_SPLITTER.getName())
                    .append(eachEntry.getValue())
                    .append(StorageEnum.LINE_SPLITTER.getName());
        }
        inputs.add(sb.toString());
        return inputs;
    }


    public Map<Integer, Integer> toMap(List<String> lines) {
        Map<Integer, Integer> integerInputs = new HashMap<>();
        final int integerMapLineNumber = 0;
        String[] splitted = split(lines, integerMapLineNumber);
        for (String eachStr : splitted) {
            String key = eachStr.split(StorageEnum.KEY_VALUE_SPLITTER.getName())[0];
            String value = eachStr.split(StorageEnum.KEY_VALUE_SPLITTER.getName())[1];
            integerInputs.put(Integer.valueOf(key), Integer.valueOf(value));
        }
        return integerInputs;
    }

    public Map<Integer, String> toStringMap(List<String> lines) {
        Map<Integer, String> stringInputs = new HashMap<>();
        final int stringMapLineNumber = 1;
        String[] splitted = split(lines, stringMapLineNumber);
        for (String eachStr : splitted) {
            String key = eachStr.split(StorageEnum.KEY_VALUE_SPLITTER.getName())[0];
            String value = eachStr.split(StorageEnum.KEY_VALUE_SPLITTER.getName())[1];
            stringInputs.put(Integer.valueOf(key), value);
        }
        return stringInputs;
    }

    private String[] split(List<String> lines, int lineNumber) {
        String[] splitted = new String[0];
        if (!lines.isEmpty()) {
            String intStr = lines.get(lineNumber);
            splitted = intStr.split(StorageEnum.LINE_SPLITTER.getName());
        }
        return splitted;
    }

}

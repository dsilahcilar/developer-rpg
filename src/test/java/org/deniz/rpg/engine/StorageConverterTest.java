package org.deniz.rpg.engine;

import org.deniz.rpg.engine.storage.StorageConverter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StorageConverterTest {
    private StorageConverter storageConverter = new StorageConverter();


    @Test
    public void toListTest() throws Exception {
        Map<Integer, Integer> intMap = new HashMap<>();
        intMap.put(0, 3);
        intMap.put(1, 2);
        intMap.put(2, 7);
        Map<Integer, String> stringMap = new HashMap<>();
        stringMap.put(0, "deniz");
        stringMap.put(1, "developer");
        List<String> response = storageConverter.toList(intMap, stringMap);

        assertThat(response.size(), is(2));
        String line1 = response.get(0);
        assertThat(line1, is("0:3,1:2,2:7,"));
        String line2 = response.get(1);
        assertThat(line2, is("0:deniz,1:developer,"));
    }

    @Test
    public void integerMapTest() throws Exception {
        List<String> lines = getLines();

        Map<Integer, Integer> response = storageConverter.toMap(lines);

        assertThat(response.size(), is(10));
        assertThat(response.get(0), is(6));
        assertThat(response.get(4), is(1));
        assertThat(response.get(6), is(2));
        assertThat(response.get(9), is(1));
    }


    @Test
    public void stringMapTest() throws Exception {
        List<String> lines = getLines();
        Map<Integer, String> response = storageConverter.toStringMap(lines);

        assertThat(response.size(), is(1));
        assertThat(response.get(0), is("deniz"));
    }

    private List<String> getLines() {
        List<String> lines = new ArrayList<>();
        String line1 = new String("0:6,1:6,2:6,3:7,4:1,5:1,6:2,7:1,8:1,9:1,");
        String line2 = new String("0:deniz,");
        lines.add(line1);
        lines.add(line2);
        return lines;
    }
}
package org.deniz.rpg.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FileHelperTest {
    private FileHelper reader = new FileHelper();

    @Test
    public void read() throws Exception {
        String expectedStr = "self:\n" +
                "  title: You need to learn jsf...\n" +
                "  description: bla bla bla\n" +
                "condition:\n" +
                "  cards:\n" +
                "    - java\n" +
                "experience: 3\n" +
                "satisfaction: 2";

        String path = getClass().getClassLoader().getResource("cards/be/lang/java/jsf.yml").getPath();
        String response = reader.read(path);

        assertThat(response, is(expectedStr));
    }

    @Test
    public void shouldListDirectories() throws URISyntaxException {
        URL path = getClass().getClassLoader().getResource("cards");
        List<String> paths = reader.paths(path.getPath());
        assertThat(paths, Matchers.containsInAnyOrder("be", "fe"));
    }

}
package org.deniz.rpg.engine;

import org.deniz.rpg.engine.game.PageGenerator;
import org.deniz.rpg.model.Card;
import org.deniz.rpg.model.Page;
import org.junit.Test;

import java.util.Optional;

import static org.deniz.rpg.builder.RootPage.rootPage;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PageGeneratorTest {
    private PageGenerator pageGenerator = new PageGenerator();

    @Test
    public void shouldBuildPageTree() throws Exception {
        String rootPath = getClass().getClassLoader().getResource("cards").getFile();
        Page expectedPage = rootPage();
        Page builtPage = pageGenerator.buildTree(rootPath);
        assertThat(builtPage.getSelf(), is(expectedPage.getSelf()));
        assertThat(builtPage.getCards().size(), is(0));
        assertThat(builtPage.getChildPages().size(), is(2));
        assertTrue(builtPage.toPage("be").isPresent());
        assertThat(builtPage.toPage("be").get().getChildPages().size(), is(2));
        assertTrue(builtPage.toPage("fe").isPresent());
        assertThat(builtPage.toPage("fe").get().getChildPages().size(), is(2));
        final Optional<Page> langPage = builtPage.toPage("be").get().toPage("lang");
        assertTrue(langPage.isPresent());
        final Optional<Page> java = langPage.get().toPage("java");
        assertTrue(java.isPresent());
        assertThat(java.get().getCards().size(), is(3));
        Card expectedCard = expectedPage.toPage("be").get().toPage("lang").get().toPage("java").get().getCards().get("java");
        assertThat(java.get().getCards().get("java"), is(expectedCard));
    }


}
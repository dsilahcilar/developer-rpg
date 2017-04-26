package org.deniz.rpg.engine;

import org.deniz.rpg.builder.BePages;
import org.deniz.rpg.builder.RootPage;
import org.deniz.rpg.engine.game.SimpleQuestionGenerator;
import org.deniz.rpg.model.Page;
import org.deniz.rpg.model.Selectable;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleQuestionGeneratorTest {
    private SimpleQuestionGenerator questionGenerator = new SimpleQuestionGenerator();

    @Test
    public void shouldGenerateFrontEndAndBackEndQuestionsWhenPageIsRoot() throws Exception {
        Page rootPage = RootPage.rootPage();

        Map<String, Selectable> questions = questionGenerator.generateQuestions(rootPage);

        assertThat(questions.size(), is(2));
        assertThat(questions.get("be").getSelf().getTitle(), is("Backend developer"));
        assertThat(questions.get("fe").getSelf().getTitle(), is("FrontEnd developer"));
    }


    @Test
    public void shouldGenerateCardsQuestions() throws Exception {
        Page javaPage = BePages.bePage().toPage("lang").get().toPage("java").get();

        Map<String, Selectable> questions = questionGenerator.generateQuestions(javaPage);

        assertThat(questions.size(), is(3));
        assertThat(questions.get("java").getSelf().getTitle(), is("You need to learn java..."));
        assertThat(questions.get("jsf").getSelf().getTitle(), is("You need to learn jsf..."));
        assertThat(questions.get("spring").getSelf().getTitle(), is("You need to learn spring..."));

    }

}
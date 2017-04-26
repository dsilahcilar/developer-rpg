package org.deniz.rpg.engine;

import org.deniz.rpg.builder.BePages;
import org.deniz.rpg.engine.game.ConditionalQuestionGenerator;
import org.deniz.rpg.engine.game.PlayerService;
import org.deniz.rpg.engine.game.SimpleQuestionGenerator;
import org.deniz.rpg.model.Page;
import org.deniz.rpg.model.Selectable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConditionalQuestionGeneratorTest {

    private SimpleQuestionGenerator simpleQuestionGenerator = new SimpleQuestionGenerator();
    @Mock
    private PlayerService playerService;

    private ConditionalQuestionGenerator conditionalQuestionGenerator;

    @Before
    public void init() {
        conditionalQuestionGenerator = new ConditionalQuestionGenerator(simpleQuestionGenerator,playerService);
    }

    @Test
    public void shouldGenerateQuestionsWhenPlayerDoesNotHaveAnyThing() throws Exception {
        Page page = BePages.bePage();
        when(playerService.hasCard("lang")).thenReturn(false);
        when(playerService.hasCard("db")).thenReturn(false);
        Map<String, Selectable> questions = conditionalQuestionGenerator.generateQuestions(page);

        assertThat(questions.size(), is(2));
    }


    @Test
    public void shouldRemoveQuestionsWhenPlayerHave() throws Exception {
        Page page = BePages.bePage();
        when(playerService.hasCard("lang")).thenReturn(true);
        Map<String, Selectable> questions = conditionalQuestionGenerator.generateQuestions(page);
        assertThat(questions.size(), is(1));
    }

}
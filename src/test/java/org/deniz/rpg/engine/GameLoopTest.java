package org.deniz.rpg.engine;

import org.deniz.rpg.builder.RootPage;
import org.deniz.rpg.engine.game.LearningService;
import org.deniz.rpg.engine.game.SimpleQuestionGenerator;
import org.deniz.rpg.io.IOHelper;
import org.deniz.rpg.io.TerminalInputService;
import org.deniz.rpg.model.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameLoopTest {
    @Mock
    private SimpleQuestionGenerator questionGenerator;
    @Mock
    private TerminalInputService inputService;
    @Mock
    private LearningService learningService;
    @Mock
    private IOHelper ioHelper;

    //private GameLoop game = new GameLoop(questionGenerator, inputService, learningService, ioHelper);

    @Test
    public void run() throws Exception {
        Page rootPage = RootPage.rootPage();
//        game.run(rootPage);
    }

}
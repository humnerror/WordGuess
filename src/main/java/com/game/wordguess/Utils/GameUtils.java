package com.game.wordguess.Utils;

import com.game.wordguess.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class GameUtils {

    @Autowired
    private ConfigurableApplicationContext context;
    private int Max_Tries = 5;

    public void reduceTry() {
        Max_Tries -= 1;
    }

    public int TriesRemaining() {
        return Max_Tries;
    }

    public GameService playAgain() {
        return context.getBean(GameService.class);
    }

    public void resetTries() {
        Max_Tries = 5;
    }
}

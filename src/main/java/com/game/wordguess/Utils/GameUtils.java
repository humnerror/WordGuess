package com.game.wordguess.Utils;

import com.game.wordguess.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class GameUtils {


    private final ConfigurableApplicationContext context;

    @Autowired
    public GameUtils(ConfigurableApplicationContext context) {
        this.context = context;
    }

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

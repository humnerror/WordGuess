package com.game.wordguess.Controller;

import com.game.wordguess.Service.GameService;
import com.game.wordguess.Utils.GameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    private GameService service;

    private final GameUtils utils;

    @Autowired
    public GameController(GameService service, GameUtils utils) {
        this.service = service;
        this.utils = utils;
    }

    @GetMapping("/home")
    public String ShowHomePage(@RequestParam(value = "guessChar", required = false) String word, Model model) {

        String randomWord = service.toString();

        boolean Win = false;
        boolean userGuessing;

        if (word!=null && word.length()>1) {
            userGuessing = service.userGuessingFullWord(word);
            randomWord = service.toString();
            Win=true;

        }
        if (word!=null && word.length()==1) {
            userGuessing = service.userGuessing(word.charAt(0));
            randomWord = service.toString();
            if (!userGuessing) {
                utils.reduceTry();
            }
            if ((!randomWord.contains("_") && utils.TriesRemaining() > 0)) {
                Win = true;
            }
        }
        model.addAttribute("meaning",service.gettingDefinitionList());
        model.addAttribute("totalLetters",service.totalLetters());
        model.addAttribute("randomWord", randomWord);
        model.addAttribute("remainingTry", utils.TriesRemaining());
        model.addAttribute("winOrLose", Win);
        return "homepage";
    }

    @GetMapping("/playAgain")
    public String PlayAgain() {
        service = utils.playAgain();
        utils.resetTries();
        return "redirect:/home";
    }
}
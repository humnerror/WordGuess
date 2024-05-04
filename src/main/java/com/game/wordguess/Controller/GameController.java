package com.game.wordguess.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.game.wordguess.Service.GameService;
import com.game.wordguess.Utils.GameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @Autowired
    private GameService service;

    @Autowired
    private GameUtils utils;


    @GetMapping("/home")
    public String ShowHomePage(@RequestParam(value = "guessChar", required = false) String word, Model model) throws JsonProcessingException {

        String randomWord = service.toString();

        boolean Win = false;
        boolean userGuessing;

        System.out.println(word);

        if (word!=null && word.length()>1) {
            userGuessing = service.userGuessingFullWord(word);
            randomWord = service.toString();
            Win=true;

        }

        else if (word!=null && word.length()==1) {
            userGuessing = service.userGuessing(word.charAt(0));
            randomWord = service.toString();
            if (!userGuessing) {
                utils.reduceTry();
            }
            if ((!randomWord.contains("_") && utils.TriesRemaining() > 0)) {
                Win = true;
            }
        }

        System.out.println("Meaning: "+service.gettingDefinition());

        model.addAttribute("meaning",service.gettingDefinition());

        System.out.println("Remaining tries: " + utils.TriesRemaining());

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
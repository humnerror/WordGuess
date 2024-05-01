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

    @Autowired
    private GameService service;

    @Autowired
    private GameUtils utils;

    @GetMapping("/home")
    public String ShowHomePage(@RequestParam(value = "guessChar", required = false) String word, Model model) {

        String randomWord = service.toString();

        System.out.println(word);

        if (word != null) {
            boolean userGuessing = service.userGuessing(word.charAt(0));
            randomWord = service.toString();
            if (!userGuessing) {
                utils.reduceTry();
            }
        }



        System.out.println("Remaining tries: " + utils.TriesRemaining());

        model.addAttribute("randomWord", randomWord);

        model.addAttribute("remainingTry", utils.TriesRemaining());

        model.addAttribute("winOrLose",service.GameWin());

        return "homepage";
    }

    @GetMapping("/playAgain")
    public String PlayAgain() {

        service = utils.playAgain();
        utils.resetTries();

        return "redirect:/home";
    }
}

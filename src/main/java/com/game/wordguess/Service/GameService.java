package com.game.wordguess.Service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Scope("prototype")
public class GameService {

    Random random = new Random();

    private final char[] allCharacterWord;

    String randomWord;


    public GameService() {
        String[] arrayOfWords = {"designation", "diabolic", "pharmacy", "clarification", "apartment", "whiskey"};
        randomWord = arrayOfWords[random.nextInt(arrayOfWords.length)];
        System.out.println("The random word is: " + randomWord);
        allCharacterWord = new char[randomWord.length()];
    }

    @Override
    public String toString() {

        String returnPage = "";

        for (char e : allCharacterWord) {
            if (e == '\u0000') {
                returnPage += "_";
            } else {
                returnPage += e;
            }
            returnPage += " ";
        }


        return returnPage;
    }

    public boolean userGuessing(char charAt) {
        boolean isCorrect = false;
        for (int i = 0; i < randomWord.length(); i++) {
            if (charAt == randomWord.charAt(i)) {
                allCharacterWord[i] = charAt;
                isCorrect = true;
            }
        }
        return isCorrect;
    }

    public boolean GameWin() {
        boolean res = true;
        for (char c : allCharacterWord) {
            if (c == '_') {
                res = false;
                break;
            }
        }
        return res;
    }
}

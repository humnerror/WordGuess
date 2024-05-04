package com.game.wordguess.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.game.wordguess.FileConverter.FileToArrayConverter;
import com.game.wordguess.FileConverter.WordAPI;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

@Service
@Scope("prototype")
public class GameService {

    private final Random random = new Random();

    private final WordAPI api = new WordAPI();

    private final char[] allCharacterWord;

    String randomWord;

    String answer;


    public GameService() throws IOException {
        String[] arrayOfWords = new FileToArrayConverter().readFileLinesToArray();
        randomWord = arrayOfWords[random.nextInt(arrayOfWords.length)];
        answer=randomWord;
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
    public String gettingDefinition() throws JsonProcessingException {
        Map<String, String> stringMap = api.GettingDefinition(randomWord);
        return stringMap.get(randomWord);
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

    public boolean userGuessingFullWord(String word){
        boolean isCorrect = true;
        System.out.println(word.length()== allCharacterWord.length);
        if(word.length()== answer.length()) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < word.length(); i++) {
                if (chars[i] != answer.charAt(i)) {
                    isCorrect = false;
                    break;
                }
            }
        }
        return isCorrect;
    }
    public int totalLetters(){
        return randomWord.length();
    }

}

package com.game.wordguess.Service;

import com.game.wordguess.FileConverter.FileToArrayConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
@Scope("prototype")
public class GameService {

    @Autowired
    private WordService wordService;

    private final Random random = new Random();

    private final char[] allCharacterWord;

    public String randomWord;

    public String answer;


    public GameService() throws IOException {
        String[] arrayOfWords = new FileToArrayConverter().readFileLinesToArray();
        randomWord = arrayOfWords[random.nextInt(arrayOfWords.length)];
        answer = randomWord;
        System.out.println("The random word is: " + randomWord);
        allCharacterWord = new char[randomWord.length()];
    }

    @Override
    public String toString() {
        StringBuilder returnPage = new StringBuilder();
        for (char e : allCharacterWord) {
            if (e == '\u0000') {
                returnPage.append("_");
            } else {
                returnPage.append(e);
            }
            returnPage.append(" ");
        }
        return returnPage.toString();
    }

    public List<String> gettingDefinitionList(){
        return wordService.getWordMeaning(randomWord);
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

    public boolean userGuessingFullWord(String word) {
        boolean isCorrect = true;
        System.out.println(word.length() == allCharacterWord.length);
        if (word.length() == answer.length()) {
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

    public int totalLetters() {
        return randomWord.length();
    }

}

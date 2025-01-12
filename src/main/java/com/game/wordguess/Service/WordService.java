package com.game.wordguess.Service;

import com.game.wordguess.Api.WordFeignClient;
import com.game.wordguess.Model.Definitions;
import com.game.wordguess.Model.Meanings;
import com.game.wordguess.Model.Word;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordFeignClient feign;

    public List<String> getWordMeaning(String input) {
        List<Word> wordList = feign.getWordAndMeanings(input);
        return extractNoun(groupByPOS(wordList)).stream()
                .limit(5)
                .toList();
    }

    private List<String> extractNoun(Map<String, List<String>> stringListMap) {
        return stringListMap.entrySet()
                .stream()
                .filter(entry-> entry.getKey().equalsIgnoreCase("noun"))
                .map(Map.Entry::getValue)
                .findFirst().orElseThrow();
    }

    private Map<String, List<String>> groupByPOS(List<Word> wordList) {
        return wordList.stream()
                .map(Word::getMeanings)
                .flatMap(List::stream)
                .collect(groupingBy(Meanings::getPartOfSpeech,
                                mapping(Meanings::getDefinitions,
                                        flatMapping(List::stream, mapping(Definitions::getDefinition, toList())))));
    }
}

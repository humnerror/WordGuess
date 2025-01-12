package com.game.wordguess.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meanings {
    private String partOfSpeech;
    private List<Definitions> definitions;
    private List<String> synonyms;
    private List<String> antonyms;
}

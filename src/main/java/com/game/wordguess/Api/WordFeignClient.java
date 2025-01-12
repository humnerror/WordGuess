package com.game.wordguess.Api;


import com.game.wordguess.Model.Word;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "word-service", url = "${feign.url}")
public interface WordFeignClient {

    @GetMapping("${feign.path}/{word}")
    List<Word> getWordAndMeanings(@PathVariable("word") String word);
}

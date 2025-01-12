package com.game.wordguess.FileConverter;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FileToArrayConverter {

    public String[] readFileLinesToArray() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/file/words.txt");
        String fileContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        String[] lines = fileContent.split("\\r?\\n");
        return Arrays.stream(lines)
                .filter(obj -> (obj.length() > 4 && obj.length() < 14))
                .toArray(String[]::new);
    }

}
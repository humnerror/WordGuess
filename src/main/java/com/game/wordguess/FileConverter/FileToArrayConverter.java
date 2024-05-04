package com.game.wordguess.FileConverter;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FileToArrayConverter {

    public String[] readFileLinesToArray() throws IOException {

        String filePath = "static/file/words.txt";
        // Load the file from the classpath
        ClassPathResource resource = new ClassPathResource(filePath);

        // Read the file content as a string
        String fileContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        // Split the file content into lines
        String[] lines = fileContent.split("\\r?\\n");

        lines = Arrays.stream(lines).filter(obj -> (obj.length() > 4 && obj.length() < 14)).toArray(String[]::new);

        return lines;
    }

}
package com.game.wordguess.FileConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FileToArrayConverter {

    @Value("${localFile.path}")
    private String filePath;

    public String[] readFileLinesToArray() throws IOException {
        // Load the file from the classpath
        ClassPathResource resource = new ClassPathResource(filePath);
        // Read the file content as a string
        String fileContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        // Split the file content into lines
        String[] lines = fileContent.split("\\r?\\n");
        return Arrays.stream(lines)
                .filter(obj -> (obj.length() > 4 && obj.length() < 14))
                .toArray(String[]::new);
    }

}
package ru.enovikow.hw_01.parser.impl;

import ru.enovikow.hw_01.parser.JsonFileParser;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.Objects.*;
import static java.util.logging.Level.WARNING;

public class JsonFileParserImpl implements JsonFileParser {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final String OPENED_BRACER = "{";
    private static final String CLOSED_BRACER = "}";
    private static final String DELIMITER = ":";
    private static final char COMMA = ',';
    private static final int KEY = 0;
    private static final int VALUE = 1;

    @Override
    public Map<String, String> fromJson(String filePath) {
        Map<String, String> params = new LinkedHashMap<>();

        try {
            if (isNull(filePath)) {
                throw new IllegalArgumentException("Filepath does not be null");
            }

            Path path = Paths.get(requireNonNull(getClass().getClassLoader().getResource(filePath)).toURI());

            BufferedReader reader = Files.newBufferedReader(path);
            String line = reader.readLine();

            if (OPENED_BRACER.equals(line)) {
                String[] row;
                while (nonNull(line)) {
                    if (!OPENED_BRACER.equals(line) && !CLOSED_BRACER.equals(line)) {
                        StringBuilder sb = new StringBuilder(line);
                        if (COMMA == sb.charAt(line.length() - 1)) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        line = sb.toString().replaceAll("\"", "");
                        row = line.split(DELIMITER);
                        if (row.length != 2 || isNull(row[VALUE]) || row[VALUE].trim().length() == 0) {
                            throw new IllegalArgumentException("Error in json format");
                        }
                        params.put(row[KEY].trim(), row[VALUE].trim());
                    }
                    line = reader.readLine();
                }
            } else {
                throw new IllegalArgumentException(format("Error while parsing json file: %s. Entity should start with {", filePath));
            }

        } catch (Exception e) {
            logger.log(WARNING, e.getMessage());
        }

        return params;
    }

}

package ru.enovikow.hw_01.parser;

import java.util.Map;

public interface JsonFileParser {
    Map<String, String> fromJson(String filePath);

}

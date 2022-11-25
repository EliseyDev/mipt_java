package ru.enovikow.hw_01.processor.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.enovikow.hw_01.parser.JsonFileParser;
import ru.enovikow.hw_01.parser.impl.JsonFileParserImpl;
import ru.enovikow.hw_01.processor.FileReaderProcessor;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class EnumFileReaderProcessorTest {
    private JsonFileParser jsonFileParser;
    private FileReaderProcessor processor;
    private Map<String, String> clientFields;
    private static final String FILE_PATH = "/clients.txt";

    @BeforeEach
    public void init() {
        jsonFileParser = mock(JsonFileParserImpl.class);
        processor = new EnumFileReaderProcessor(jsonFileParser);
        clientFields = prepareEntitiesFields();
    }

    @Test
    void jsonParse_always_calledOnce() {
        clientFields.put("clientType", "HOLDING");
        when(jsonFileParser.fromJson(FILE_PATH)).thenReturn(clientFields);
        processor.process(FILE_PATH);
        verify(jsonFileParser, times(1)).fromJson(FILE_PATH);
    }

    @Test
    void processor_always_calledOnce() {
        clientFields.put("clientType", "HOLDING");
        when(jsonFileParser.fromJson(FILE_PATH)).thenReturn(clientFields);
        processor = mock(StringFileReaderProcessor.class);
        processor.process(FILE_PATH);
        verify(processor, times(1)).process(FILE_PATH);
    }

    private Map<String, String> prepareEntitiesFields() {
        Map<String, String> entitiesFields = new LinkedHashMap<>();
        entitiesFields.put("name", "testName");
        entitiesFields.put("inn", "123412");

        return entitiesFields;
    }
}
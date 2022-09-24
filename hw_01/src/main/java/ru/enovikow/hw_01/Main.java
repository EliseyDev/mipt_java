package ru.enovikow.hw_01;

import ru.enovikow.hw_01.entity.ReaderType;
import ru.enovikow.hw_01.parser.JsonFileParser;
import ru.enovikow.hw_01.parser.impl.JsonFileParserImpl;
import ru.enovikow.hw_01.processor.FileReaderProcessor;
import ru.enovikow.hw_01.processor.impl.EnumFileReaderProcessor;
import ru.enovikow.hw_01.processor.impl.StringFileReaderProcessor;
import ru.enovikow.hw_01.service.FileReaderService;
import ru.enovikow.hw_01.service.impl.FileReaderServiceImpl;

import static java.util.Objects.nonNull;

public class Main {
    public static void main(String[] args) {
        String filePath = args[0];

        if (nonNull(filePath)) {
            JsonFileParser jsonFileParser = new JsonFileParserImpl();
            FileReaderProcessor stringProcessor = new StringFileReaderProcessor(jsonFileParser);
            FileReaderProcessor enumProcessor = new EnumFileReaderProcessor(jsonFileParser);
            FileReaderService service = new FileReaderServiceImpl(stringProcessor, enumProcessor);

            service.readFile(filePath, ReaderType.STRING);
            service.readFile(filePath, ReaderType.ENUM);
        }
    }
}
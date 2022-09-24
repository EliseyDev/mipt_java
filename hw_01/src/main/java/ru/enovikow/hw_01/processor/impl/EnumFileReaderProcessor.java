package ru.enovikow.hw_01.processor.impl;

import ru.enovikow.hw_01.entity.Client;
import ru.enovikow.hw_01.entity.ClientType;
import ru.enovikow.hw_01.parser.JsonFileParser;
import ru.enovikow.hw_01.processor.FileReaderProcessor;

import java.util.Map;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class EnumFileReaderProcessor implements FileReaderProcessor {

    private static final Logger logger = Logger.getAnonymousLogger();

    private static final String CLIENT_TYPE = "clientType";
    private final JsonFileParser jsonFileParser;

    public EnumFileReaderProcessor(JsonFileParser jsonFileParser) {
        this.jsonFileParser = jsonFileParser;
    }

    @Override
    public void process(String fileName) {
        Map<String, String> entityFields = jsonFileParser.fromJson(fileName);

        ClientType clientType = ClientType.valueOf(entityFields.get(CLIENT_TYPE));
        Client client = clientType.createClient(entityFields);

        logger.log(INFO, client.toString());
    }
}

package ru.enovikow.hw_01.processor.impl;

import ru.enovikow.hw_01.entity.*;
import ru.enovikow.hw_01.parser.JsonFileParser;
import ru.enovikow.hw_01.processor.FileReaderProcessor;

import java.util.Map;
import java.util.logging.Logger;

import static java.util.Objects.nonNull;
import static java.util.logging.Level.INFO;
import static ru.enovikow.hw_01.entity.ClientType.valueOf;

public class StringFileReaderProcessor implements FileReaderProcessor {
    private static final Logger logger = Logger.getAnonymousLogger();
    private final JsonFileParser jsonFileParser;

    private static final String CLIENT_TYPE = "clientType";

    public StringFileReaderProcessor(JsonFileParser jsonParser) {
        this.jsonFileParser = jsonParser;
    }

    @Override
    public void process(String fileName) {
        Map<String, String> entityFields = jsonFileParser.fromJson(fileName);
        String client = entityFields.get(CLIENT_TYPE);

        if (nonNull(client)) {
            ClientType clientType = valueOf(entityFields.get(CLIENT_TYPE));
            Client entity;

            switch (clientType) {
                case HOLDING -> {
                    entity = buildHolding(entityFields);
                    logger.log(INFO, entity.toString());
                }
                case INDIVIDUAL -> {
                    entity = buildIndividual(entityFields);
                    logger.log(INFO, entity.toString());
                }
                case LEGAL_ENTITY -> {
                    entity = buildLegalEntity(entityFields);
                    logger.log(INFO, entity.toString());
                }
            }
        }
    }

    private Holding buildHolding(Map<String, String> entityFields) {
        return Holding.builder()
                .name(entityFields.get("name"))
                .inn(Integer.parseInt(entityFields.get("inn")))
                .address("address")
                .build();
    }

    private LegalEntity buildLegalEntity(Map<String, String> entityFields) {
        return LegalEntity.builder()
                .name(entityFields.get("name"))
                .inn(Integer.parseInt(entityFields.get("inn")))
                .address(entityFields.get("address"))
                .build();
    }

    private Individual buildIndividual(Map<String, String> entityFields) {
        return Individual.builder()
                .name(entityFields.get("name"))
                .inn(Integer.parseInt(entityFields.get("inn")))
                .age(35)
                .build();
    }

}

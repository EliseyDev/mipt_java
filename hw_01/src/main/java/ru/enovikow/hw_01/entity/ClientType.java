package ru.enovikow.hw_01.entity;

import java.util.Map;

import static java.lang.Integer.parseInt;

public enum ClientType {
    INDIVIDUAL {
        @Override
        public Client createClient(Map<String, String> entityFields) {
            int inn;
            int age;
            try {
                inn = parseInt(entityFields.get("inn"));
                age = parseInt(entityFields.get("age"));
            } catch (NumberFormatException e) {
                inn = 0;
                age = 0;
            }

            return Individual.builder()
                    .name(entityFields.get("name"))
                    .inn(inn)
                    .age(age)
                    .build();
        }
    },
    LEGAL_ENTITY {
        @Override
        public Client createClient(Map<String, String> entityFields) {
            int inn;
            try {
                inn = parseInt(entityFields.get("inn"));
            } catch (NumberFormatException e) {
                inn = 0;
            }
            return LegalEntity.builder()
                    .name(entityFields.get("name"))
                    .inn(inn)
                    .address(entityFields.get("address"))
                    .build();
        }
    },
    HOLDING {
        @Override
        public Client createClient(Map<String, String> entityFields) {
            int inn;
            try {
                inn = parseInt(entityFields.get("inn"));
            } catch (NumberFormatException e) {
                inn = 0;
            }
            return Holding.builder()
                    .name(entityFields.get("name"))
                    .inn(inn)
                    .address(entityFields.get("address"))
                    .build();
        }
    };

    public abstract Client createClient(Map<String, String> entityFields);
}

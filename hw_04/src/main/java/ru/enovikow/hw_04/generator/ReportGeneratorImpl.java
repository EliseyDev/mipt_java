package ru.enovikow.hw_04.generator;

import ru.enovikow.hw_04.report.Report;
import ru.enovikow.hw_04.report.ReportImpl;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

public class ReportGeneratorImpl<T> implements ReportGenerator<T> {
    @Override
    public Report generate(List<T> entities) {
        if (isNotEmpty(entities)) {

            Map<String, List<Object>> fields = new LinkedHashMap<>();
            entities.forEach(entity -> {
                Field[] entityFields = entity.getClass().getDeclaredFields();
                for (Field field : entityFields) {
                    field.setAccessible(true);

                    String fieldName;
                    Object fieldValue;

                    try {
                        fieldName = field.getName();
                        fieldValue = field.get(entity);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                    List<Object> list = fields.get(fieldName);

                    if (nonNull(list)) {
                        list.add(fieldValue);
                    } else {
                        List<Object> fieldValues = new LinkedList<>();
                        fieldValues.add(fieldValue);
                        fields.put(fieldName, fieldValues);
                    }

                }
            });

            fields.forEach((key, value) -> {
                System.out.println(key);
                value.forEach(System.out::println);
            });
            return new ReportImpl(fields);
        }

        return null;
    }
}

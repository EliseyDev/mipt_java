package ru.enovikow.hw_04.generator;

import ru.enovikow.hw_04.report.Report;

import java.util.List;

public interface ReportGenerator<T> {
    Report generate(List<T> entities);
}

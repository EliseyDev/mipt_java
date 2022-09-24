package ru.enovikow.hw_01.service;

import ru.enovikow.hw_01.entity.ReaderType;

public interface FileReaderService {
    void readFile(String fileName, ReaderType type);
}

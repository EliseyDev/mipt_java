package ru.enovikow.hw_01.service.impl;

import ru.enovikow.hw_01.entity.ReaderType;
import ru.enovikow.hw_01.processor.FileReaderProcessor;
import ru.enovikow.hw_01.service.FileReaderService;

public class FileReaderServiceImpl implements FileReaderService {
    private final FileReaderProcessor stringProcessor;
    private final FileReaderProcessor enumProcessor;

    public FileReaderServiceImpl(FileReaderProcessor stringProcessor, FileReaderProcessor enumProcessor) {
        this.stringProcessor = stringProcessor;
        this.enumProcessor = enumProcessor;
    }

    @Override
    public void readFile(String fileName, ReaderType readerType) {
        switch (readerType) {
            case STRING -> stringProcessor.process(fileName);
            case ENUM -> enumProcessor.process(fileName);
        }
    }


}

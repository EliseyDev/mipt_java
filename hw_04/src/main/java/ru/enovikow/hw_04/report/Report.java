package ru.enovikow.hw_04.report;

import java.io.OutputStream;

public interface Report {
    byte[] asBytes();

    void writeTo(OutputStream os);
}

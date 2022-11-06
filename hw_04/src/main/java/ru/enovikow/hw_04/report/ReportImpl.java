package ru.enovikow.hw_04.report;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.isNull;
import static java.util.logging.Logger.getAnonymousLogger;

public class ReportImpl implements Report {

    Logger logger = getAnonymousLogger();
    private final Map<String, List<Object>> entities;

    public ReportImpl(Map<String, List<Object>> entities) {
        this.entities = entities;
    }

    @Override
    public byte[] asBytes() {
        byte[] array = null;

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(entities);
            oos.flush();

            array = bos.toByteArray();

        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return array;
    }

    @Override
    public void writeTo(OutputStream os) {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("report");

        AtomicInteger rowCounter = new AtomicInteger(1);
        AtomicInteger headerCellCounter = new AtomicInteger(0);
        AtomicInteger cellCounter = new AtomicInteger(0);

        entities.forEach((s, objects) -> {
            Row row = sheet.getRow(0);
            if (isNull(row)) {
                row = sheet.createRow(0);
            }

            Cell headerCell = row.createCell(headerCellCounter.getAndIncrement());
            headerCell.setCellValue(s);

            objects.forEach(o -> {
                Row tableRow = sheet.getRow(rowCounter.getAndIncrement());
                if (isNull(tableRow)) {
                    tableRow = sheet.createRow(rowCounter.intValue() - 1);
                }

                Cell cell = tableRow.createCell(cellCounter.intValue());
                cell.setCellValue(o.toString());
            });

            rowCounter.set(1);
            cellCounter.incrementAndGet();

        });

        sheet.autoSizeColumn(1);

        try {
            book.write(os);
            book.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

    }

}

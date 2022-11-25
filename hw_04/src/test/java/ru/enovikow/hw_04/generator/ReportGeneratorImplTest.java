package ru.enovikow.hw_04.generator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.enovikow.hw_04.entity.Owner;
import ru.enovikow.hw_04.report.Report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

class ReportGeneratorImplTest {

    @Test
    public void report_always_isGenerated() {
        ReportGeneratorImpl<Owner> reportGenerator = new ReportGeneratorImpl<>();
        Report report = reportGenerator.generate(getMockedEntities());

        try {
            OutputStream os = new FileOutputStream("example.xlsx");
            report.writeTo(os);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void expectIOException_always_thanOutputStreamIsNull() {
        ReportGeneratorImpl<Owner> reportGenerator = new ReportGeneratorImpl<>();
        Report report = reportGenerator.generate(getMockedEntities());

        Assertions.assertThatThrownBy(() -> report.writeTo(null));

    }

    private List<Owner> getMockedEntities() {
        Owner owner = new Owner("Elisey", 36);
        Owner owner2 = new Owner("Ivan", 39);
        Owner owner3 = new Owner("Sergey", 27);

        return Arrays.asList(owner, owner2, owner3);
    }

}
package com.application.matching.parser;

import com.application.matching.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CSVParserTest {
    private static final String PATH_TO_FILE = "src/test/resources/employee csv 8.csv";

    @Autowired
    IParser parser;

    @Test
    void parseWithCSV() throws IOException {
        IParser parser = new CSVParser();
        InputStream inputStream = new FileInputStream(PATH_TO_FILE);
        List<Employee> entities = parser.parse(inputStream, ".csv");
        assertNotNull(entities);
        assertFalse(entities.isEmpty());
    }

    @Test
    void parseWithChain() throws IOException {
        InputStream inputStream = new FileInputStream(PATH_TO_FILE);
        List<Employee> entities = parser.parse(inputStream, ".csv");
        assertNotNull(entities);
        assertFalse(entities.isEmpty());
    }
}

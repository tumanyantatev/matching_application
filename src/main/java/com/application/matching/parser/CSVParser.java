package com.application.matching.parser;

import com.application.matching.model.Employee;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSVParser extends AbstractParser {
    private static final String CSV_FORMAT = ".csv";

    private static final String HEADER_NAME = "Name";
    private static final String HEADER_EMAIL = "Email";
    private static final String HEADER_DIVISION = "Division";
    private static final String HEADER_AGE = "Age";
    private static final String HEADER_TIMEZONE_OFFSET = "Timezone";

    public List<Employee> parse(InputStream inputStream, String extension) throws IOException {
        if (CSV_FORMAT.equalsIgnoreCase(extension)) {
            Reader in = new InputStreamReader(inputStream);

            List<Employee> employees;

            try {
                List<CSVRecord> records = CSVFormat.DEFAULT.withHeader().withFirstRecordAsHeader().parse(in).getRecords();
                employees = new ArrayList<>(records.size());
                for (CSVRecord record : records) {
                    Employee e = new Employee(record.get(HEADER_NAME), record.get(HEADER_EMAIL), record.get(HEADER_DIVISION),
                            Integer.parseInt(record.get(HEADER_AGE)), Integer.parseInt(record.get(HEADER_TIMEZONE_OFFSET)));
                    employees.add(e);
                }
            } catch (IOException e) {
                throw new IOException("Failed to parse the file. " + e.getMessage(), e);
            }

            return employees;
        } else {
            return next(inputStream, extension);
        }
    }
}

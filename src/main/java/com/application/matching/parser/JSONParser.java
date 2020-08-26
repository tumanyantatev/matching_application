package com.application.matching.parser;

import com.application.matching.model.Employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JSONParser extends AbstractParser {
    @Override
    public List<Employee> parse(InputStream inputStream, String extension) throws IOException {
        return next(inputStream, extension);
    }
}

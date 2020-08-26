package com.application.matching.parser;

import com.application.matching.model.Employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class AbstractParser implements IParser {
    protected IParser next;

    public void setNext(IParser parser) {
        next = parser;
    }

    protected List<Employee> next(InputStream inputStream, String extension) throws IOException {
        return next.parse(inputStream, extension);
    }
}

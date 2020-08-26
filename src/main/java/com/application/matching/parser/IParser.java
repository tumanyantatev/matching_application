package com.application.matching.parser;


import com.application.matching.model.Employee;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Parsing is implemented as a chain of responsibility design pattern
 */
public interface IParser {
    @NonNull
    List<Employee> parse(InputStream inputStream, String extension) throws IOException;
    void setNext(IParser parser);
}

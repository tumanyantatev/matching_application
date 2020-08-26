package com.application.matching.service;

import com.application.matching.criteria.CriteriaHandler;
import com.application.matching.model.Employee;
import com.application.matching.model.MatchingResult;
import com.application.matching.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MatchingService {

    @Autowired
    IParser parser;

    public MatchingResult getMatchingResult(MultipartFile file) throws IOException {
        String extension = getFileExtension(file);
        List<Employee> employees;
        employees = parser.parse(file.getInputStream(), extension);
        return MatchingEngine.getMatchedPairs(employees, CriteriaHandler.getCriteria());
    }

    private String getFileExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();
        return filename.substring(filename.lastIndexOf("."));
    }
}

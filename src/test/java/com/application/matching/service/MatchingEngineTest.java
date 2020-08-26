package com.application.matching.service;

import com.application.matching.criteria.CriteriaHandler;
import com.application.matching.criteria.MatchingCriteriaAdvanced;
import com.application.matching.criteria.MatchingCriteriaBasic;
import com.application.matching.model.Employee;
import com.application.matching.model.MatchingResult;
import com.application.matching.parser.CSVParser;
import com.application.matching.parser.IParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MatchingEngineTest {
    private static final String PATH_TO_FILE = "src/test/resources/employee csv 8.csv";

    @Test
    void getMatchedPairs() throws IOException {
        IParser parser = new CSVParser();
        InputStream inputStream = new FileInputStream(PATH_TO_FILE);
        List<Employee> employees = parser.parse(inputStream, ".csv");
        MatchingResult matchedPairs = MatchingEngine.getMatchedPairs(employees, CriteriaHandler.getCriteria());
        assertNotNull(matchedPairs);
        assertTrue(matchedPairs.getAverageMatchingScore() > 0);
        assertEquals(5, matchedPairs.getPairings().size());
        assertEquals(58, matchedPairs.getAverageMatchingScore());
    }
}

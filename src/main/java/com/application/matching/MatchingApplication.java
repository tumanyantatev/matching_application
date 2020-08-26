package com.application.matching;

import com.application.matching.parser.CSVParser;
import com.application.matching.parser.IParser;
import com.application.matching.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class MatchingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatchingApplication.class, args);
    }

    @Bean
    IParser getParsersChain() {
        IParser jsonParser = new JSONParser();
        CSVParser csvParser = new CSVParser();
        jsonParser.setNext(csvParser);
        return jsonParser;
    }
}

package com.application.matching.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MatchingResult {
    private double averageMatchingScore;
    private List<MatchedPairWithScore> pairings;
}

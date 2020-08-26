package com.application.matching.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MatchedPairWithScore {
    private Employee firstEmployee;
    private Employee secondEmployee;
    private int score;
}

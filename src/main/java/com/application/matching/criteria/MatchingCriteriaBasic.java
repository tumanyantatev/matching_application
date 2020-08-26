package com.application.matching.criteria;

import com.application.matching.model.Employee;

public class MatchingCriteriaBasic implements IMatchingCriteria {

    /*
        These values can be stored in some file/db as preferences
        so that the changes in values are reflected automatically without recompiling the application.
     */
    private static final int AGE_MATCH_PERCENT_SCORE = 30;
    private static final int DIVISION_MATCH_PERCENT_SCORE = 30;
    private static final int TIMEZONE_MATCH_PERCENT_SCORE = 40;

    private static final int AGE_ACCEPTABLE_DIFF = 5;
    private static final int TIMEZONE_ACCEPTABLE_DIFF = 0;

    MatchingCriteriaBasic() {}

    public int match(Employee e1, Employee e2) {
        int score = 0;

        if (e1.getDivision().equalsIgnoreCase(e2.getDivision())) {
            score += DIVISION_MATCH_PERCENT_SCORE;
        }

        if (Math.abs(e1.getAge() - e2.getAge()) <= AGE_ACCEPTABLE_DIFF) {
            score += AGE_MATCH_PERCENT_SCORE;
        }

        if (Math.abs(e1.getTimezoneOffset() - e2.getTimezoneOffset()) <= TIMEZONE_ACCEPTABLE_DIFF) {
            score += TIMEZONE_MATCH_PERCENT_SCORE;
        }
        return score;
    }

    @Override
    public boolean isValid() {
        return AGE_MATCH_PERCENT_SCORE + DIVISION_MATCH_PERCENT_SCORE + TIMEZONE_MATCH_PERCENT_SCORE == 100;
    }
}

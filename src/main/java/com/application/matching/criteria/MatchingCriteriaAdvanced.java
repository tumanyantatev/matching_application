package com.application.matching.criteria;

import com.application.matching.model.Employee;

public class MatchingCriteriaAdvanced implements IMatchingCriteria {

    MatchingCriteriaAdvanced() {}

    @Override
    public int match(Employee e1, Employee e2) {
        // TBD
        return 100;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}

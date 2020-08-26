package com.application.matching.criteria;

import com.application.matching.model.Employee;

public interface IMatchingCriteria {
    int match(Employee e1, Employee e2);
    boolean isValid();
}

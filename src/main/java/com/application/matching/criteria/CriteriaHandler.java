package com.application.matching.criteria;

public class CriteriaHandler {

    private static IMatchingCriteria matchingCriteria;


    public static IMatchingCriteria getCriteria() {
        // if there are several criteria which a company can choose from
        // here the decision which one to instantiate will be done based on some property
        // that indicates which criteria is selected

/*         if (newCriteria == prevSelectedCriteria) {
             return matchingCriteria;
         } else {
             // determine what's the class for the new criteria and instantiate it
         }*/

        matchingCriteria = new MatchingCriteriaBasic();

        if (!matchingCriteria.isValid()) {
            throw new RuntimeException("Criteria scores are not valid.");
        }

        return matchingCriteria;
    }
}

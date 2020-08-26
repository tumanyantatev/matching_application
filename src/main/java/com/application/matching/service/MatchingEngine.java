package com.application.matching.service;

import com.application.matching.criteria.IMatchingCriteria;
import com.application.matching.model.Employee;
import com.application.matching.model.MatchedPairWithScore;
import com.application.matching.model.MatchingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchingEngine {

    public static MatchingResult getMatchedPairs(List<Employee> employees, IMatchingCriteria criteria) {
        int[][] scores = constructScoreMatrix(employees, criteria);
        int[] matchings = getMatchings(scores);
        MatchingResult result = constructMatchingResult(matchings, scores, employees);
        return result;
    }

    private static MatchingResult constructMatchingResult(int[] matchings, int[][] scores, List<Employee> employees) {
        MatchingResult result = new MatchingResult();
        int scoreSum = 0;
        boolean[] alreadyPaired = new boolean[matchings.length];

        List<MatchedPairWithScore> pairings = new ArrayList<>(employees.size() / 2);
        for (int i = 1; i < matchings.length; ++i) {
            if (!alreadyPaired[i] && i != matchings[i]) {
                alreadyPaired[i] = alreadyPaired[matchings[i]] = true;
                Employee e1 = employees.get(i - 1);
                Employee e2 = employees.get(matchings[i] - 1);
                int score = -scores[i][matchings[i]];
                MatchedPairWithScore pairWithScore = new MatchedPairWithScore(e1, e2,  score);
                pairings.add(pairWithScore);
                scoreSum += score;
            }
        }

        result.setPairings(pairings);
        result.setAverageMatchingScore((double)scoreSum / pairings.size());
        return result;
    }

    /**
     * <p>
     * Using <a href="https://en.wikipedia.org/wiki/Hungarian_algorithm">Hungarian algorithm</a> for assignment problem.
     * Construction of bipartite graph with matrix representation is done in {@link MatchingEngine#constructScoreMatrix(java.util.List, com.application.matching.criteria.IMatchingCriteria)}.
     * </p>
     * <p>
     * Rows are the first part, columns - second.
     * On the diagonal there are zeros as we should not match employees to themselves.
     * </p>
     */
    private static int[] getMatchings(int[][] scores) {
        int n = scores.length - 1;

        // potential
        int[] u = new int[n + 1]; // for first part  (rows)
        int[] v = new int[n + 1]; // for second part (columns)

        // indicates the pairs
        // matching[i] will be the index of employee who is assigned to employee with index i
        int[] matching = new int[n + 1];

        // holds in order the indices of elements in the path from the second part of bipartite graph (columns)
        int[] augmentingPath = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            matching[0] = i;
            int currentColumn = 0;
            int[] minValues = new int[n + 1];
            Arrays.fill(minValues, Integer.MAX_VALUE);
            boolean[] used = new boolean[n + 1];
            do {
                used[currentColumn] = true;
                int matchedRow = matching[currentColumn];
                int delta = Integer.MAX_VALUE;
                int indexMinReached = 0;
                for (int j = 1; j <= n; ++j)
                    if (!used[j]) {
                        int cur = scores[matchedRow][j] - u[matchedRow] - v[j];
                        if (cur < minValues[j]) {
                            minValues[j] = cur;
                            augmentingPath[j] = currentColumn;
                        }
                        if (minValues[j] < delta) {
                            delta = minValues[j];
                            indexMinReached = j;
                        }
                    }
                for (int j = 0; j <= n; ++j) {
                    if (used[j]) {
                        u[matching[j]] += delta;
                        v[j] -= delta;
                    } else {
                        minValues[j] -= delta;
                    }
                }
                currentColumn = indexMinReached;
            } while (matching[currentColumn] != 0);

            // augmenting path is found
            // so we can increase the path by 1 by alternating the selected edges (add another pairing)
            do {
                int nextMatchedColumnIndex = augmentingPath[currentColumn];
                matching[currentColumn] = matching[nextMatchedColumnIndex];
                currentColumn = nextMatchedColumnIndex;
            } while (currentColumn != 0);
        }

        return matching;
    }

    /**
     * From the given set of employees as vertices constructing a bipartite graph
     * by duplication the vertices thus getting a square matrix.
     */
    private static int[][] constructScoreMatrix(List<Employee> employees, IMatchingCriteria criteria) {
        int n = employees.size();
        // adding one row and one column filled with 0s as the those will be used as helpers
        int[][] scores = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; ++i) {
            for (int j = i + 1; j < n + 1; ++j) {
                int matchScore = criteria.match(employees.get(i - 1), employees.get(j - 1));

                // flipping the sign as the algorithm finds the pairs with minimum sum
                scores[i][j] = scores[j][i] = -1 * matchScore;
            }
        }
        return scores;
    }
}

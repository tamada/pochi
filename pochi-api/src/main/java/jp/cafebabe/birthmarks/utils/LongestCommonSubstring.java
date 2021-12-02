package jp.cafebabe.birthmarks.utils;

import jp.cafebabe.birthmarks.entities.Couple;

import java.util.Optional;

public class LongestCommonSubstring {
    public static final String of(String s1, String s2) {
        return new LongestCommonSubstring()
                .calculate(s1, s2);
    }

    private String calculate(String s1, String s2) {
        int[][] table = new int[s1.length() + 1][s2.length() + 1];
        Result r = Result.of(0, 0, 0);
        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table[i].length; j++) {
                if(i == 0 || j == 0) table[i][j] = 0;
                else if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    table[i][j] = 1 + table[i - 1][j - 1];
                    r = updateResultIfNeeded(r, i, j, table[i][j]);
                }
                // printTable(table);
            }
        }
        return s1.substring(r.i - r.max, r.i);
    }

    private Result updateResultIfNeeded(Result r, int i, int j, int v) {
        if(r.max > v)
            return r;
        return Result.of(i, j, v);
    }

    private static final class Result {
        private int i, j;
        private int max;
        private Result(int i, int j, int max) {
            this.i = i;
            this.j = j;
            this.max = max;
        }
        public static Result of(int i, int j, int max) {
            return new Result(i, j, max);
        }
    }
}

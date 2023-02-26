package com.adventofcode;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Collections;

@Log4j2
public class Question08 {


    public static int first(String source) {
        String[] rows = source.split("\n");
        int numberOfVisibleTrees = 0;
        // loop every row except outer 2
        for (int rowIndex = 1; rowIndex < rows.length - 1; rowIndex++) {
            String row = rows[rowIndex];
            // loop every character except outer 2
            for (int treeIndex = 1; treeIndex < row.length() - 1; treeIndex++) {
                // if is visible from any direction, increment counter and move to next tree
                if (checkUp(rows, rowIndex, treeIndex)) {
                    numberOfVisibleTrees += 1;
                    continue;
                }
                if (checkLeft(row, treeIndex)) {
                    numberOfVisibleTrees += 1;
                    continue;
                }
                if (checkDown(rows, rowIndex, treeIndex)) {
                    numberOfVisibleTrees += 1;
                    continue;
                }
                if (checkRight(row, treeIndex)) {
                    numberOfVisibleTrees += 1;
                }
            }
        }
        return numberOfVisibleTrees + ((rows.length * 4) - 4);
    }

    public static int second(String source) {
        String[] rows = source.split("\n");
        int bestViewingScore = 0;
        // loop every row except outer 2
        for (int rowIndex = 1; rowIndex < rows.length - 1; rowIndex++) {
            String row = rows[rowIndex];
            // loop every character except outer 2
            for (int treeIndex = 1; treeIndex < row.length() - 1; treeIndex++) {
                int viewingScore = Question08.countAllVisibleTrees(rows, rowIndex, treeIndex);
                if (viewingScore > bestViewingScore) {
                    bestViewingScore = viewingScore;
                }
            }
        }
        return bestViewingScore;
    }

    protected static int countAllVisibleTrees(String[] rows, int rowIndex, int treeIndex) {
        int totalVisible = 1;
        totalVisible *= countRight(rows[rowIndex], treeIndex);
        totalVisible *= countUp(rows, rowIndex, treeIndex);
        totalVisible *= countDown(rows, rowIndex, treeIndex);
        totalVisible *= countLeft(rows[rowIndex], treeIndex);
        return totalVisible;
    }

    protected static int countRight(String row, int treeIndex) {
        int sourceTreeHeight = Integer.parseInt(String.valueOf(row.charAt(treeIndex)));
        String[] restOfRow = row.substring(treeIndex + 1).split("");
        return countVisibleTreesOnRowOrColumn(sourceTreeHeight, restOfRow);
    }

    protected static int countLeft(String row, int treeIndex) {
        int sourceTreeHeight = Integer.parseInt(String.valueOf(row.charAt(treeIndex)));
        String restOfRow = row.substring(0, treeIndex);
        String[] reverseRestOfRow = new StringBuilder()
            .append(restOfRow)
            .reverse()
            .toString()
            .split("");
        return countVisibleTreesOnRowOrColumn(sourceTreeHeight, reverseRestOfRow);
    }

    protected static int countDown(String[] rows, int rowIndex, int treeIndex) {
        int sourceTreeHeight = Integer.parseInt(String.valueOf(rows[rowIndex].charAt(treeIndex)));
        String[] column = Arrays.stream(rows)
            .map(row -> String.valueOf(row.charAt(treeIndex)))
            .toList()
            .toArray(new String[0]);
        String[] restOfColumn = Arrays.copyOfRange(column, rowIndex + 1, rows.length);
        return countVisibleTreesOnRowOrColumn(sourceTreeHeight, restOfColumn);
    }


    protected static int countUp(String[] rows, int rowIndex, int treeIndex) {
        int sourceTreeHeight = Integer.parseInt(String.valueOf(rows[rowIndex].charAt(treeIndex)));
        String[] column = Arrays.stream(rows)
            .map(row -> String.valueOf(row.charAt(treeIndex)))
            .toList()
            .toArray(new String[0]);
        String[] restOfColumn = Arrays.copyOfRange(column, 0, rowIndex);
        Collections.reverse(Arrays.asList(restOfColumn));
        return countVisibleTreesOnRowOrColumn(sourceTreeHeight, restOfColumn);
    }

    private static int countVisibleTreesOnRowOrColumn(int sourceTreeHeight, String[] restOfRow) {
        int numberOfVisibleTrees = 0;
        for (String tree : restOfRow) {
            int targetTree = Integer.parseInt(tree);
            numberOfVisibleTrees += 1;
            if (targetTree >= sourceTreeHeight) {
                break;
            }
        }
        return numberOfVisibleTrees;
    }

    protected static boolean checkRight(String row, int treeIndex) {
        int targetTreeHeight = Integer.parseInt(String.valueOf(row.charAt(treeIndex)));
        String restOfRow = row.substring(treeIndex + 1);
        boolean isEqualOrHigherTree = Arrays.stream(restOfRow.split(""))
            .map(Integer::parseInt)
            .anyMatch(integer -> integer >= targetTreeHeight);
        if (isEqualOrHigherTree) {
            return false;
        } else {
            return true;
        }
    }

    protected static boolean checkLeft(String row, int treeIndex) {
        int targetTreeHeight = Integer.parseInt(String.valueOf(row.charAt(treeIndex)));
        String restOfRow = row.substring(0, treeIndex);
        boolean isEqualOrHigherTree = Arrays.stream(restOfRow.split(""))
            .map(Integer::parseInt)
            .anyMatch(integer -> integer >= targetTreeHeight);
        if (isEqualOrHigherTree) {
            return false;
        } else {
            return true;
        }
    }

    protected static boolean checkUp(String[] rows, int rowIndex, int treeIndex) {
        int targetTreeHeight = Integer.parseInt(String.valueOf(rows[rowIndex].charAt(treeIndex)));
        String[] restOfRows = Arrays.copyOfRange(rows, 0, rowIndex);
        return isHighestTree(treeIndex, targetTreeHeight, restOfRows);
    }

    protected static boolean checkDown(String[] rows, int rowIndex, int treeIndex) {
        int targetTreeHeight = Integer.parseInt(String.valueOf(rows[rowIndex].charAt(treeIndex)));
        String[] restOfRows = Arrays.copyOfRange(rows, rowIndex + 1, rows.length);
        return isHighestTree(treeIndex, targetTreeHeight, restOfRows);
    }

    private static boolean isHighestTree(int treeIndex, int targetTreeHeight, String[] restOfRows) {
        for (String row: restOfRows) {
            int otherTreeHeight = Integer.parseInt(String.valueOf(row.charAt(treeIndex)));
            if (otherTreeHeight >= targetTreeHeight) {
                return false;
            }
        }
        return true;
    }
}

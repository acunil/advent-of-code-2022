package com.adventofcode;

public class Question02 {

    protected static String formatSource(String source) {
        return source.replace("\n", ",")
            .replace(" ", "");
    }

    public static int getSecondScore(String source) {
        source = formatSource(source);
        String[] list = source.split(",");
        int secondScore = 0;
        for (String round : list) {
            char enemy = round.charAt(0);
            char result = round.charAt(1);
            // X = loss
            // Y = draw
            // Z = win

            if (result == 'X') {
                secondScore += handleLoss(enemy);
            }

            if (result == 'Y') {
                secondScore += handleDraw(enemy);
            }

            if (result == 'Z') {
                secondScore += handleWin(enemy);
            }
        }
        return secondScore;
    }

    public static int getTotalScore(String source) {
        source = formatSource(source);
        String[] list = source.split(",");
        int totalScore = 0;
        for (String round : list) {
            char enemy = round.charAt(0);
            char mine = round.charAt(1);
            if (enemy == 'A') {
                totalScore += handleEnemyRock(String.valueOf(mine));
            }
            if (enemy == 'B') {
                totalScore += handleEnemyPaper(String.valueOf(mine));
            }
            if (enemy == 'C') {
                totalScore += handleEnemyScissors(String.valueOf(mine));
            }
        }
        return totalScore;
    }

    int getNumberFromXYZ(String XYZ) {
        if (XYZ.equals("X")) {
            return 1;
        }
        if (XYZ.equals("Y")) {
            return 2;
        }
        if (XYZ.equals("Z")) {
            return 3;
        }
        return 0;
    }

    static int handleEnemyRock(String myChoice) {
        if (myChoice.equals("X")) {
            return 3 + 1;
        }
        if (myChoice.equals("Y")) {
            return 6 + 2;
        }
        if (myChoice.equals("Z")) {
            return 0 + 3;
        }
        throw new IllegalStateException("unrecognised " + myChoice);
    }

    static int handleEnemyPaper(String myChoice) {
        if (myChoice.equals("X")) {
            return 0 + 1;
        }
        if (myChoice.equals("Y")) {
            return 3 + 2;
        }
        if (myChoice.equals("Z")) {
            return 6 + 3;
        }
        throw new IllegalStateException("unrecognised " + myChoice);
    }

    static int handleEnemyScissors(String myChoice) {
        if (myChoice.equals("X")) {
            return 6 + 1;
        }
        if (myChoice.equals("Y")) {
            return 0 + 2;
        }
        if (myChoice.equals("Z")) {
            return 3 + 3;
        }
        throw new IllegalStateException("unrecognised " + myChoice);
    }

    static int handleWin(char enemy) {
        if (enemy == 'A') {
            return handleEnemyRock("Y");
        }
        if (enemy == 'B') {
            return handleEnemyPaper("Z");
        }
        if (enemy == 'C') {
            return handleEnemyScissors("X");
        }
        throw new IllegalStateException("unrecognised " + enemy);
    }

    static int handleDraw(char enemy) {
        if (enemy == 'A') {
            return handleEnemyRock("X");
        }
        if (enemy == 'B') {
            return handleEnemyPaper("Y");
        }
        if (enemy == 'C') {
            return handleEnemyScissors("Z");
        }
        throw new IllegalStateException("unrecognised " + enemy);
    }

    static int handleLoss(char enemy) {
        if (enemy == 'A') {
            return handleEnemyRock("Z");
        }
        if (enemy == 'B') {
            return handleEnemyPaper("X");
        }
        if (enemy == 'C') {
            return handleEnemyScissors("Y");
        }
        throw new IllegalStateException("unrecognised " + enemy);
    }
}

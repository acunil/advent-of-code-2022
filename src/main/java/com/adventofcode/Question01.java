package com.adventofcode;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

@Log4j2
public class Question01 {


    protected static String formatSource(String source) {
        String commaSeparated = source.replace("\n\n", "|");
        String noNewLines = commaSeparated.replace("\n", ",");
        String replaceNewLines = noNewLines.replace("|", "\n");
        return replaceNewLines;
    }

    public static int first(String source) {
        String formatSource = formatSource(source);
        Object[] listOfTotals = getListOfTotals(formatSource);
        return (int) listOfTotals[0];
    }

    public static int second(String source) {
        String formatSource = formatSource(source);
        Object[] listOfTotals = getListOfTotals(formatSource);
        return IntStream.of(0, 1, 2).map(i -> (int) listOfTotals[i]).sum();
    }

    private static Object[] getListOfTotals(String data) {
        String[] list = data.split("\n");
        return Arrays.stream(list).map(Question01::getTotalOfIndividualElf)
            .sorted(Comparator.reverseOrder()).toArray();
    }


    private static int getTotalOfIndividualElf(String firstGuy) {
        String[] firstGuyList = firstGuy.split(",");
        int firstGuyTotalCalories = 0;
        for (String s : firstGuyList) {
            firstGuyTotalCalories += Integer.parseInt(s);
        }
        return firstGuyTotalCalories;
    }
}

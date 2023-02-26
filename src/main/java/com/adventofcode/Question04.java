package com.adventofcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question04 {
    static final Pattern PATTERN = Pattern.compile("((\\d+)-(\\d+)),((\\d+)-(\\d+))");

    private enum Coverage {
        FULL,
        PARTIAL
    }

    void example() {
        String testString = "a-b,c-d\ne-f,g-h";
        Pattern testPattern = Pattern.compile("(\\D-\\D),(\\D-\\D)");
        Matcher m = testPattern.matcher(testString);
        while (m.find()) {
            System.out.println("group 1: " + m.group(1));
            System.out.println("group 2: " + m.group(2));
        }
    }

    public static int first(String source) {
        return calculateNumberCovered(source, Coverage.FULL);
    }

    public static int second(String source) {
        return calculateNumberCovered(source, Coverage.PARTIAL);
    }


    private static int calculateNumberCovered(String source, Coverage coverage) {
        var allCombinations = getAllCombinations(source);
        int covered = 0;

        for (List<Integer> pairsOfPlots : allCombinations) {
            List<Integer> plots1 = getCoveredPlots(pairsOfPlots.get(0), pairsOfPlots.get(1));
            List<Integer> plots2 = getCoveredPlots(pairsOfPlots.get(2), pairsOfPlots.get(3));
            if (coverage == Coverage.FULL) {
                covered += arePlotsFullyCovered(plots1, plots2) ? 1 : 0;
            } else {
                covered += arePlotsPartiallyCovered(plots1, plots2) ? 1 : 0;
            }
        }
        return covered;
    }

    public static int firstAlt(String source) {
        var allCombinations = getAllCombinations(source);
        int covered = 0;

        for (List<Integer> pairsOfPlots : allCombinations) {
            covered += arePlotsFullyCovered(pairsOfPlots) ? 1 : 0;
        }
        return covered;
    }


    private static List<Integer> getCoveredPlots(Integer start, Integer end) {
        ArrayList<Integer> plots = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            plots.add(i);
        }
        return plots;
    }

    private static boolean arePlotsFullyCovered(List<Integer> pairOfPlots) {
        Integer plot1Start = pairOfPlots.get(0);
        Integer plot1End = pairOfPlots.get(1);
        Integer plot2Start = pairOfPlots.get(2);
        Integer plot2End = pairOfPlots.get(3);

        boolean plot2ContainsPlot1 = (plot1Start >= plot2Start && plot1Start <= plot2End) && (plot1End >= plot2Start && plot1End <= plot2End);
        boolean plot1ContainsPlot2 = (plot2Start >= plot1Start && plot2Start <= plot1End) && (plot2End >= plot1Start && plot2End <= plot1End);
        return plot1ContainsPlot2 || plot2ContainsPlot1;
    }


    private static boolean arePlotsFullyCovered(List<Integer> list1, List<Integer> list2) {
        return list1.containsAll(list2) || list2.containsAll(list1);
    }

    private static boolean arePlotsPartiallyCovered(List<Integer> list1, List<Integer> list2) {
        return !Collections.disjoint(list1, list2);
    }

    private static ArrayList<List<Integer>> getAllCombinations(String source) {
        ArrayList<List<Integer>> all = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(source);

        while (matcher.find()) {
            int number1 = Integer.parseInt(matcher.group(2));
            int number2 = Integer.parseInt(matcher.group(3));
            int number3 = Integer.parseInt(matcher.group(5));
            int number4 = Integer.parseInt(matcher.group(6));
            List<Integer> ints = List.of(number1, number2, number3, number4);
            all.add(ints);
        }

        return all;
    }
}

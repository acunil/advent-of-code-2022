package com.adventofcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question03 {

    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public static int first(String source) {
        String[] listOfRucksacks = source.split("\n");
        int prioritySum = 0;

        for (String rucksack : listOfRucksacks) {
            List<String> compartments = getCompartments(rucksack);
            char duplicate = findDuplicate(compartments);
            prioritySum += getPriority(duplicate);
        }
        return prioritySum;
    }

    public static int second(String source) {
        String[] listOfRucksacks = source.split("\n");

        var groupsOfThree = getGroupsOfThree(listOfRucksacks);

        int prioritySum = 0;

        for (List<String> group : groupsOfThree) {
            char duplicate = findDuplicateForGroup(group);
            prioritySum += getPriority(duplicate);
        }
        return prioritySum;
    }


    static List<String> getCompartments(String rucksack) {
        int rucksackLength = rucksack.length();
        String compartment1 = rucksack.substring(0, rucksackLength / 2);
        String compartment2 = rucksack.substring(rucksackLength / 2, rucksackLength);
        return List.of(compartment1, compartment2);
    }

    static int getPriority(char item) {
        return ALPHABET.indexOf(item) + 1;
    }

    static char findDuplicate(List<String> compartments) {
        String[] compartment1 = compartments.get(0).split("");
        String[] compartment2 = compartments.get(1).split("");

        for (String item : compartment1) {
            if (Arrays.asList(compartment2).contains(item)) {
                return item.charAt(0);
            }
        }

        throw new IllegalArgumentException();
    }

    static char findDuplicateForGroup(List<String> groupOfThree) {
        String[] rucksack1 = groupOfThree.get(0).split("");
        String[] rucksack2 = groupOfThree.get(1).split("");
        String[] rucksack3 = groupOfThree.get(2).split("");

        for (String item : rucksack1) {
            if (Arrays.asList(rucksack2).contains(item)
                && Arrays.asList(rucksack3).contains(item)) {
                return item.charAt(0);
            }
        }

        throw new IllegalArgumentException();
    }

    static List<List<String>> getGroupsOfThree(String[] listOfRucksacks) {
        List<List<String>> groupsOfThree = new ArrayList<>();

        List<String> group = new ArrayList<>();

        for (String rucksack : listOfRucksacks) {
            group.add(rucksack);
            if (group.size() == 3) {
                groupsOfThree.add(group);
                group = new ArrayList<>();
            }
        }

        return groupsOfThree;
    }

}

package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a Rucksack containing
 * two compartments.
 */
public class Rucksack {
    public static int totalItemPriorityCounter = 0;
    /**
     * The offset needed to full range of 'A' to 'Z' value from 27 to 52.
     */
    private static final int capitalLetterOffset = 38;

    /**
     * The offset needed to full range of 'a' to 'z' value from 1 to 26.
     */
    private static final int lowerLetterOffset = 96;

    /**
     * Using a HashSet for better lookup perfomance O(1).
     * Holds the first part of the elements in the rucksack.
     */
    private List<Character> compartmentOne;


    /**
     * Holds the second part of the elements in the rucksack.
     */
    private List<Character> compartmentTwo;

    private String compartmentTwoAsString;

    private int itemPriority;

    public static List<Rucksack> convertToRucksacks(List<String> lines) {
        List<Rucksack> rucksacks = new ArrayList<>();

        for (String line : lines)
        {
            rucksacks.add(new Rucksack(line));
        }

        return rucksacks;
    }

    public Rucksack(String content) {
        if (content == null || content.isEmpty())
            throw new IllegalArgumentException("The content of the rucksack mustn't be null or empty.");

        this.compartmentOne = new ArrayList<>();
        this.compartmentTwo = new ArrayList<>();

        fillCompartments(content);
        Character character = getDoubleElement();

        this.itemPriority = calculateItemPriority(character);

        Rucksack.totalItemPriorityCounter += this.itemPriority;
    }

    public static int calculateItemPriority(Character item)
    {
        if (Character.isUpperCase(item))
            return (int)item - Rucksack.capitalLetterOffset;
        else
            return (int)item - Rucksack.lowerLetterOffset;
    }

    public List<Character> getAllElements()
    {
        List<Character> result = new ArrayList<>();
        result.addAll(this.compartmentOne);
        result.addAll(this.compartmentTwo);
        return result;
    }

    public boolean contains(Character character)
    {
        return this.compartmentOne.contains(character) || this.compartmentTwo.contains(character);
    }

    /**
     * Fills the compartments of the Rucksack
     *
     * @param content The content divided in the middle.
     */
    private void fillCompartments(String content) {
        String firstCompartment = content.substring(0, content.length() / 2);
        String secondCompartment = content.substring(content.length() / 2, content.length());

        this.compartmentTwoAsString = secondCompartment;

        this.compartmentOne = firstCompartment
                .chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());

        this.compartmentTwo = secondCompartment
                .chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());
    }

    private Character getDoubleElement()
    {
        for (Character character : this.compartmentOne)
        {
            int index = compartmentTwoAsString.indexOf(character);

            if (index != - 1)
            {
                return character;
            }
        }

        throw new IllegalStateException("There is no double element in both compartments");
    }
}


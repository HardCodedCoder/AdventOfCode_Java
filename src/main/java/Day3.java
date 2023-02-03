package main.java;

import java.io.IOException;
import java.util.List;
import java.util.SimpleTimeZone;

public class Day3
{
    /**
     * The offset needed to full range of 'A' to 'Z' value from 27 to 52.
     */
    private static final int capitalLetterOffset = 38;

    /**
     * The offset needed to full range of 'a' to 'z' value from 1 to 26.
     */
    private static final int lowerLetterOffset = 64;

    /**
     * Main entry point of the application.
     * @param args Command line arguments passed to the executable.
     */
    public static void main(String[] args)
    {
        FileReader reader = new FileReader();
        List<String> stringRucksacks = null;
        try
        {
            stringRucksacks = reader.readFile("src/main/resources/day3_input.txt");
        }
        catch (IllegalArgumentException | IOException e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        List<Rucksack> rucksacks = Rucksack.convertToRucksacks(stringRucksacks);

        System.out.format("Priority of all double items: %d", Rucksack.totalItemPriorityCounter);
        System.out.format("The sum of all priorities of item types: %d", calculateItemPriorityOfAllGroups(rucksacks));
    }

    private static int calculateItemPriorityOfAllGroups(List<Rucksack> rucksacks)
    {
        int counter = 0;
        for (int groupIdx = 0; groupIdx <= rucksacks.size()- 3; groupIdx += 3)
        {
            for (Character character : rucksacks.get(groupIdx).getAllElements())
            {
                if (!rucksacks.get(groupIdx + 1).contains(character))
                    continue;

                if (!rucksacks.get(groupIdx + 2).contains(character))
                    continue;

                 counter += Rucksack.calculateItemPriority(character);

                 break;
            }
        }

        return counter;
    }
}

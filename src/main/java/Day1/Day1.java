package Day1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Day1
{
    public static void main(String[] args)
    {
        List<String> puzzleInput = readPuzzleInput("src/main/resources/day1_input.txt");
        int highestWeight = getHighestWeightFromPuzzleInput(puzzleInput);
        System.out.format("The elf with the heighest weight carries: %d calories%n", highestWeight);

        List<Integer> allWeights = getWeightsOfAllElfs(puzzleInput);
        Collections.sort(allWeights, Collections.reverseOrder());
        int topThreeTotalCounter = allWeights.get(0) + allWeights.get(1) + allWeights.get(2);
        System.out.format("The three elfs carry together: %d%n", topThreeTotalCounter);
    }

    public static List<String> readPuzzleInput(String path) throws IllegalArgumentException
    {
        File file = new File(path);
        try
        {
            return Files.readAllLines(file.toPath());
        }
        catch (IOException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static List<Integer> getWeightsOfAllElfs(List<String> puzzleInput)
    {
        int currentCounter = 0;
        List<Integer> allweights = new ArrayList<>();

        for (String line : puzzleInput)
        {
            if (line.equals(""))
            {
                allweights.add(currentCounter);
                currentCounter = 0;
                continue;
            }
            currentCounter += Integer.parseInt(line);
        }

        return allweights;
    }

    public static int getHighestWeightFromPuzzleInput(List<String> puzzleInput)
    {
        int currentCounter = 0;
        int highestWeight = 0;

        for (String line : puzzleInput)
        {
            if (line.equals(""))
            {
                if (currentCounter > highestWeight)
                    highestWeight = currentCounter;

                currentCounter = 0;
                continue;
            }

            currentCounter += Integer.parseInt(line);
        }

        return highestWeight;
    }
}

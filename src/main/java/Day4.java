
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import main.java.FileReader;

public class Day4
{
    private enum ToCount {
        TotalAmountOfFullyContainedRanges,
        TotalOverlaps
    }
    public static void main(String[] args)
    {
        FileReader reader = new FileReader();
        List<String> puzzleInput = null;
        try
        {
           puzzleInput = reader.readFile("src/main/resources/day4_input.txt");
        }
        catch (IllegalArgumentException | IOException e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        System.out.format("Total number of double ranges: %d", count(puzzleInput, ToCount.TotalAmountOfFullyContainedRanges));
        System.out.format("Total number of overlaps: %d", count(puzzleInput, ToCount.TotalOverlaps));
    }

    public static int count(List<String> puzzleInput, ToCount toCount)
    {
        int counter = 0;

        for (String line : puzzleInput)
        {
            String [] split = line.split(",");

            if (split.length != 2)
            {
                System.err.println("The input file is not of correct format. Exiting...");
                System.exit(2);
            }

            List<List<Integer>> ranges = new ArrayList<>();

            for (String splitLine : split)
            {
                String[] minMax = splitLine.split("-");

                if (split.length != 2)
                {
                    System.err.println("The ranges are not in the correct format. Exiting...");
                    System.exit(2);
                }

                Integer min = Integer.parseInt(minMax[0]);
                Integer max = Integer.parseInt(minMax[1]);

                List<Integer> range = IntStream.rangeClosed(min, max)
                                                .boxed()
                                                .toList();

                ranges.add(range);
            }

            switch (toCount)
            {
                case TotalAmountOfFullyContainedRanges:
                    if (ranges.get(0).containsAll(ranges.get(1)) || ranges.get(1).containsAll(ranges.get(0)))
                        counter++;
                    break;
                case TotalOverlaps:
                    if (ranges.get(0).stream().anyMatch(element -> ranges.get(1).contains(element)))
                        counter++;
            }

        }

        return counter;
    }
}

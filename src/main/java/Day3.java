import java.io.File;
import java.io.IOException;
import java.util.List;

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
        List<String> rucksacks;
        try
        {
            rucksacks = reader.readFile("src/main/resources/day3_input.txt");
        }
        catch (IllegalArgumentException | IOException e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }


    }
}

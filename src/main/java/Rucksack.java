import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

/**
 * This class represents a Rucksack containing
 * two compartments.
 */
public class Rucksack
{
    /**
     * Using a HashSet for better lookup perfomance O(1).
     * Holds the first part of the elements in the rucksack.
     */
    private List<Character> compartmentOne;

    /**
     * Holds the second part of the elements in the rucksack.
     */
    private List<Character> compartmentTwo;

    public Rucksack(String content)
    {
        if (content == null || content.isEmpty())
            throw new IllegalArgumentException("The content of the rucksack mustn't be null or empty.");

        this.compartmentOne = new ArrayList<>();
        this.compartmentTwo = new ArrayList<>();

        fillCompartments(content);
    }

    /**
     * Fills the compartments of the Rucksack
     * @param content The content divided in the middle.
     */
    private void fillCompartments(String content) {
        String firstCompartment = content.substring(0, content.length() / 2);
        String secondCompartment = content.substring(content.length() / 2, content.length() - 1);

        this.compartmentOne = firstCompartment
                .chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());

        this.compartmentTwo = secondCompartment
                .chars()
                .mapToObj(e -> (char)e)
                .collect(Collectors.toList());
    }


}

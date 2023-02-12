package Day6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day6
{
    public static void main(String [] main)
    {
        FileReader reader = new FileReader();
        List<String> puzzleInput = new ArrayList<>();
        try
        {
            puzzleInput = reader.readFile("src/main/resources/day6_input.txt");
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        String communicationStream = puzzleInput.get(0);
        int startOfPacketMarkerIndex = indexOfPacketMarker(communicationStream);
        System.out.println(startOfPacketMarkerIndex);
    }

    private static int indexOfPacketMarker(String communicationStream)
    {
        int result = -1;
        int endIdx = 14;
        for (int idx = 0; idx < communicationStream.length(); idx++)
        {
            String substring = communicationStream.substring(idx, endIdx);
            HashSet<Character> tmp = new HashSet<>();

            for (char character : substring.toCharArray())
                tmp.add(character);

            if (tmp.size() == 14)
                return endIdx;
            else
                endIdx++;
        }

        return -1;
    }
}

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class Day2
{
    public enum HandSignal
    {
        Rock,
        Paper,
        Scissors
    }

    private static final Map<String, HandSignal> stringToHandSignalMapping = Map.of(
            "A", HandSignal.Rock,
            "B", HandSignal.Paper,
            "C", HandSignal.Scissors,
            "X", HandSignal.Rock,
            "Y", HandSignal.Paper,
            "Z", HandSignal.Scissors
    );

    private static final Map<HandSignal, Integer> scoreMapping = Map.of(
            HandSignal.Rock, 1,
            HandSignal.Paper, 2,
            HandSignal.Scissors, 3
    );

    private static final Map<HandSignal, HandSignal> handSignalBeatingMap = Map.of(
            HandSignal.Rock, HandSignal.Scissors,
            HandSignal.Scissors, HandSignal.Paper,
            HandSignal.Paper, HandSignal.Rock
    );

    private static final Map<HandSignal, HandSignal> handSignalLoosingMap = Map.of(
            HandSignal.Rock, HandSignal.Paper,
            HandSignal.Paper, HandSignal.Scissors,
            HandSignal.Scissors, HandSignal.Rock
    );

    public static void main(String[] args)
    {
        // The guide handed over by the elf.
        List<String> guide = readPuzzleInput();

        int score = calculateWinningChances(guide);
        System.out.format("Your score would be: %d%n", score);

        int guideScore = evaluateScoreOfStrategyGuide(guide);
        System.out.format("Your score according to guide would be: %d", guideScore);
    }

    private static int evaluateScoreOfStrategyGuide(List<String> guide)
    {
        int score = 0;

        for (String round : guide)
        {
            String[] split = round.split(" ");

            if (split.length != 2)
            {
                System.err.format("The round: %s could not be parsed as it was not of format R1 R2! Skipping line...", round);
                continue;
            }

            HandSignal opponent = stringToHandSignalMapping.get(split[0]);
            HandSignal needed = calculateNeededHandSignal(opponent, split[1]);

            score += evaluatePlayersScore(opponent, needed);
        }

        return score;
    }

    private static HandSignal calculateNeededHandSignal(HandSignal opponent, String result)
    {
        return switch (result) {
            case "X" -> handSignalBeatingMap.get(opponent);
            case "Y" -> opponent;
            case "Z" -> handSignalLoosingMap.get(opponent);
            default -> HandSignal.Scissors;
        };
    }

    private static int evaluatePlayersScore(HandSignal opponent, HandSignal mine)
    {
        int score = scoreMapping.get(mine);

        HandSignal whatIWouldBeat = handSignalBeatingMap.get(mine);

        if (opponent == whatIWouldBeat)
            score+=6;
        else if (mine == opponent)
            score+=3;

        return score;
    }

    private static int calculateWinningChances(List<String> guide)
    {
        int myScore = 0;

        for (String round : guide)
        {
            String[] split = round.split(" ");

            if (split.length != 2)
            {
                System.err.format("The round: %s could not be parsed as it was not of format R1 R2! Skipping line...", round);
                continue;
            }

            HandSignal opponentMove = stringToHandSignalMapping.get(split[0]);
            HandSignal myMove = stringToHandSignalMapping.get(split[1]);

            myScore += evaluatePlayersScore(opponentMove, myMove);
        }

        return myScore;
    }

    public static List<String> readPuzzleInput()
    {
        List<String> puzzleInput = null;

        try
        {
            puzzleInput = readFile("src/main/resources/day2_input.txt");
        }
        catch (IOException | IllegalArgumentException e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        return puzzleInput;
    }

    public static List<String> readFile(String path) throws IllegalArgumentException,
                                                                    IOException
    {
        File file = new File(path);

        if (!file.exists())
            throw new IllegalArgumentException("The provided file does not exist!");

        return Files.readAllLines(file.toPath());
    }
}

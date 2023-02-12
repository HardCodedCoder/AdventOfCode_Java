import java.io.IOException;
import java.util.*;
import main.java.FileReader;
public class Day5
{
    private static class Command
    {
        private final int amountToMove;

        private final int from;

        private final int to;

        public Command(int amountToMove, int from, int to)
        {
            this.amountToMove = amountToMove;
            this.from = from;
            this.to = to;
        }

        public int getAmountToMove() {
            return amountToMove;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }
    }

    public static void main(String[] args)
    {
        FileReader reader = new FileReader();
        List<String> puzzleInput = null;
        try
        {
            puzzleInput = reader.readFile("src/main/resources/day5_input.txt");
        }
        catch (IllegalArgumentException | IOException e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        var cargoArea = createCargoArea(puzzleInput);
        var commands = parseCommands(puzzleInput);
        shiftCargoUsingCrateMove9001(cargoArea, commands);
        printTopOfCargo(cargoArea);
    }

    private static void printTopOfCargo(Map<Integer, Stack<Character>> cargoArea)
    {
        String output = "";
        for (var entry : cargoArea.entrySet())
        {
            output += entry.getValue().pop();
        }

        System.out.println(output);
    }

    private static void shiftCargoUsingCrateMove9001(Map<Integer, Stack<Character>> cargoArea, Iterable<Command> commands)
    {
        for (Command command : commands)
        {
            Deque<Character> cargoToMove = new ArrayDeque<>();
            for (int amountCount = 1; amountCount <= command.getAmountToMove(); amountCount++)
                cargoToMove.add(cargoArea.get(command.getFrom()).pop());

            int cargoSize = cargoToMove.size();
            for (int i = 0; i < cargoSize; i++)
            {
                cargoArea.get(command.getTo()).push(cargoToMove.getLast());
                cargoToMove.removeLast();
            }
        }
    }

    private static void shiftCargo(Map<Integer, Stack<Character>> cargoArea, Iterable<Command> commands)
    {
        for (Command command : commands)
        {
            for (int amountCount = 1; amountCount <= command.getAmountToMove(); amountCount++)
            {
                Character toMove = cargoArea.get(command.getFrom()).pop();
                cargoArea.get(command.getTo()).push(toMove);
            }
        }
    }


    private static Iterable<Command> parseCommands(List<String> puzzleInput)
    {
        int indexOfEmptyLine = puzzleInput.indexOf("");
        List<Command> commands = new ArrayList<>();
        for (int idx = indexOfEmptyLine + 1; idx < puzzleInput.size(); idx++)
        {
            String [] tmp = puzzleInput.get(idx).split(" ");

            int amountToMove = Integer.parseInt(tmp[1]);
            int from = Integer.parseInt(tmp[3]);
            int to = Integer.parseInt(tmp[5]);

            commands.add(new Command(amountToMove,from, to));
        }

        return commands;
    }

    private static Map<Integer, Stack<Character>> createCargoArea(List<String> puzzleInput)
    {
        // Get Number of Stacks
        int indexOfEmptyLine = puzzleInput.indexOf("");
        String[] split = puzzleInput.get(indexOfEmptyLine - 1).trim().split(" ");
        List<String> cargoAreaNumbers = Arrays.stream(split).filter(element -> !element.equals("")).toList();

        Map<Integer, Stack<Character>> cargoArea = new HashMap<>();

        for (String cargoAreaNumber : cargoAreaNumbers)
            cargoArea.put(Integer.parseInt(cargoAreaNumber), new Stack<Character>());

        for (int lineIdx = indexOfEmptyLine - 2; lineIdx >= 0; lineIdx--)
        {
            String currentLine = puzzleInput.get(lineIdx);
            int stackCounter = 0;

            for (int lineColumnIdx = 1; lineColumnIdx < currentLine.length(); lineColumnIdx+=4)
            {
                Character currentChar = currentLine.charAt(lineColumnIdx);
                Integer currentStackKey = Integer.parseInt(cargoAreaNumbers.get(stackCounter));
                if (currentChar != ' ')
                    cargoArea.get(currentStackKey).push(currentChar);
                stackCounter++;
            }
        }

        return cargoArea;
    }
}

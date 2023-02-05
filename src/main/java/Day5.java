package main.java;

import java.io.IOException;
import java.util.*;

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
        shiftCargo(cargoArea, commands);
        System.out.println("Hallo");
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

    public static Map<Integer, Stack<Character>> createCargoArea(List<String> puzzleInput) {
        Map<Integer, Stack<Character>> cargoArea = new HashMap<>();

        // Get Number of Stacks
        int indexOfEmptyLine = puzzleInput.indexOf("");
        String[] split = puzzleInput.get(indexOfEmptyLine - 1).trim().split(" ");
        List<String> tmp = new ArrayList<>(Arrays.stream(split).toList());
        tmp.removeIf(element -> element.isEmpty());
        int numberOfStacks = Integer.parseInt(tmp.get(tmp.size() - 1));

        // Create Stacks
        List<Stack> stacks = new ArrayList<>();
        for (int j = 1; j <= numberOfStacks; j++) {
            stacks.add(new Stack<Character>());
        }

        for (int idx = indexOfEmptyLine - 2; idx >= 0; idx--) {
            String line = puzzleInput.get(idx);
            int counter = 0;

            for (int stackIdx = 1; stackIdx < line.length() - 2; stackIdx++) {
                if (line.charAt(stackIdx) != ' ')
                    stacks.get(counter).push(line.charAt(stackIdx));

                stackIdx += 3;
                counter++;
            }
        }

        for (int idx = 0; idx < stacks.size(); idx++)
            cargoArea.put(Integer.parseInt(tmp.get(idx)), stacks.get(idx));

        return cargoArea;
    }
}

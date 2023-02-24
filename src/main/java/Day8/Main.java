package Day8;

import General.FileReader;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public record Tree(Integer tree, Integer up, Integer down, Integer left, Integer right)
    {
        public boolean isEdge()
        {
            if (left == null || right == null || down == null || up == null)
                return true;
            else
                return false;
        }

        public boolean isVisible() {
            if (this.isEdge())
                return true;
            else if (left < tree || right < tree|| up < tree || down < tree )
                return true;
            else
                return false;
        }
    }

    public static void main(String[] args)
    {
        FileReader reader = new FileReader();
        List<String> puzzleInput = new ArrayList<>();
        try
        {
            puzzleInput = reader.readFile("src/main/resources/day8_input.txt");
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        System.out.println(countVisibleTrees(puzzleInput));
    }

    public static int countVisibleTrees(List<String> treeGrid)
    {
        if (treeGrid == null)
            return 0;
        else if (treeGrid.size() == 0)
            return 0;

        int rowCount = treeGrid.size();
        int columnCount = treeGrid.get(0).length();
        Tree[][] grid = new Tree[rowCount][columnCount];

        int visibleTreeCounter = 0;
        int highestViewingScore = 0;
        for (int rowIndex = 0; rowIndex < grid.length; rowIndex++)
        {
            String currentLine = treeGrid.get(rowIndex);

            for (int columnIndex = 0; columnIndex < grid[0].length; columnIndex++)
            {
                if (rowIndex == 0 && columnIndex == 0)
                {
                    Tree tree = new Tree
                            (
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex))),
                                    null,
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex + 1).charAt(columnIndex))),
                                    null,
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex + 1)))
                            );
                    visibleTreeCounter++;
                }
                else if (rowIndex == 0 && columnIndex == grid[0].length - 1)
                {
                    Tree tree = new Tree
                            (
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex))),
                                    null,
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex + 1).charAt(columnIndex))),
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex).charAt(columnIndex - 1))),
                                    null
                            );
                    visibleTreeCounter++;
                }
                else if (rowIndex == grid.length - 1 && columnIndex == 0)
                {
                    Tree tree = new Tree
                            (
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex))),
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex - 1).charAt(columnIndex))),
                                   null,
                                    null,
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex + 1)))
                            );
                    visibleTreeCounter++;
                }
                else if (rowIndex == grid.length - 1 && columnIndex == grid[0].length - 1)
                {
                    Tree tree = new Tree
                            (
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex))),
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex - 1).charAt(columnIndex))),
                                    null,
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex).charAt(columnIndex - 1))),
                                    null
                            );
                    visibleTreeCounter++;
                }
                else if (rowIndex == 0 && columnIndex > 0)
                {
                    Tree tree = new Tree
                            (
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex))),
                                    null,
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex + 1).charAt(columnIndex))),
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex).charAt(columnIndex - 1))),
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex + 1)))
                            );
                    visibleTreeCounter++;
                }
                else if (rowIndex == grid.length - 1 && columnIndex > 0)
                {
                    Tree tree = new Tree
                            (
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex))),
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex - 1).charAt(columnIndex))),
                                    null,
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex).charAt(columnIndex - 1))),
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex + 1)))
                            );
                    visibleTreeCounter++;
                }
                else if (rowIndex > 0 && columnIndex == 0)
                {
                    Tree tree = new Tree
                            (
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex))),
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex - 1).charAt(columnIndex))),
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex + 1).charAt(columnIndex))),
                                    null,
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex + 1)))
                            );
                    visibleTreeCounter++;
                }
                else if (rowIndex > 0 && columnIndex == grid[0].length - 1)
                {
                    Tree tree = new Tree
                            (
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex))),
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex - 1).charAt(columnIndex))),
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex + 1).charAt(columnIndex))),
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex).charAt(columnIndex - 1))),
                                    null
                            );
                    visibleTreeCounter++;
                }
                else
                {
                    Tree tree = new Tree
                            (
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex))),
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex - 1).charAt(columnIndex))),
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex + 1).charAt(columnIndex))),
                                    Integer.parseInt(String.valueOf(treeGrid.get(rowIndex).charAt(columnIndex - 1))),
                                    Integer.parseInt(String.valueOf(currentLine.charAt(columnIndex + 1)))
                            );

                    boolean visibleLeft = true;
                    boolean visibleRight = true;
                    boolean visibleTop = true;
                    boolean visibleDown = true;

                    int viewingScoreLeft = 0;
                    int viewingScoreRight = 0;
                    int viewingScoreDown = 0;
                    int viewingScoreUp = 0;

                    for (int leftSideIndex = columnIndex - 1; leftSideIndex >= 0; leftSideIndex--)
                    {
                        viewingScoreLeft++;
                        if (Integer.parseInt(String.valueOf(currentLine.charAt(leftSideIndex))) >= tree.tree) {
                            visibleLeft = false;
                            break;
                        }
                    }

                    for (int rightSideIndex = columnIndex + 1; rightSideIndex < grid[0].length; rightSideIndex++)
                    {
                        viewingScoreRight++;
                        if (Integer.parseInt(String.valueOf(currentLine.charAt(rightSideIndex))) >= tree.tree) {
                            visibleRight = false;
                            break;
                        }
                    }

                    for (int topSideIndex = rowIndex -1; topSideIndex >= 0; topSideIndex--)
                    {
                        viewingScoreUp++;

                        if (Integer.parseInt(String.valueOf(treeGrid.get(topSideIndex).charAt(columnIndex))) >= tree.tree) {
                            visibleTop = false;
                            break;
                        }
                    }

                    for (int downSideIndex = rowIndex + 1; downSideIndex < grid.length; downSideIndex++)
                    {
                        viewingScoreDown++;
                        if (Integer.parseInt(String.valueOf(treeGrid.get(downSideIndex).charAt(columnIndex))) >= tree.tree) {
                            visibleDown = false;
                            break;
                        }
                    }

                    int tmpViewingScore = viewingScoreLeft * viewingScoreRight * viewingScoreUp * viewingScoreDown;
                    if (tmpViewingScore > highestViewingScore)
                        highestViewingScore = tmpViewingScore;
                    if (visibleLeft || visibleRight || visibleTop || visibleDown)
                        visibleTreeCounter++;
                }
            }
        }

        System.out.format("Highest Viewing score: %d%n", highestViewingScore);
        return visibleTreeCounter;
    }
}

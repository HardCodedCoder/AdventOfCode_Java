package Day7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;

public class DirectoryTest
{
    @Test
    public void SetComparesDirectoryByEqualsMethod()
    {
        Directory one = new Directory("Hallo");
        Directory two = new Directory("Hallo");
        Directory three = new Directory("Test");
        System.out.println(one.equals(two));
        HashSet<Directory> test = new HashSet<>();
        test.add(one);
        test.add(two);
        test.add(three);
        Assertions.assertEquals(2, test.size());
    }

    @Test
    public void DirectoryContainsGivenFilename()
    {
        Directory root = new Directory("root");
        String filename = "hello world.txt";
        File file = new File(filename);
        Assertions.assertTrue(root.add(file));
        Assertions.assertEquals(true, root.contains(filename));
    }

    @Test
    public void ChangingToChildDirectorySucceeds()
    {
        FileSystem fs = new FileSystem();
        Directory parentDirectory = fs.getCurrentDirectory();
        Assertions.assertTrue(fs.getCurrentDirectory().isEmpty());
        String dirName = "TestDir";
        fs.getCurrentDirectory().add(new Directory(dirName, fs.getCurrentDirectory()));
        Directory childDir = fs.getCurrentDirectory().getDirectory(dirName);
        fs.setCurrentDirectory(childDir);
        Assertions.assertTrue(fs.getCurrentDirectory().getName().equals(dirName));
        Assertions.assertEquals(parentDirectory, fs.getCurrentDirectory().getParent());
    }

    @Test
    public void SizeReturnsCorrectSize() {
        FileSystem fs = new FileSystem();
        int totalSize = 12345;
        int sizeOne = totalSize;
        int sizeTwo = 726534;
        totalSize+= sizeTwo;
        int sizeThree = 27320;
        totalSize+= sizeThree;
        int sizeFour = 182920;
        totalSize += sizeFour;
        int sizeFive = 17272;
        totalSize += sizeFive;
        int sizeSix = 120474;
        totalSize += sizeSix;
        fs.getCurrentDirectory().add(new File(fs.getCurrentDirectory(), "hello World.txt", sizeOne));
        fs.getCurrentDirectory().add(new Directory("empty", fs.getCurrentDirectory()));
        fs.getCurrentDirectory().add(new Directory("Other", fs.getCurrentDirectory()));
        fs.changeDirectory("Other");
        fs.getCurrentDirectory().add(new Directory("empty", fs.getCurrentDirectory()));
        fs.getCurrentDirectory().add(new Directory("empty2", fs.getCurrentDirectory()));
        fs.getCurrentDirectory().add(new File(fs.getCurrentDirectory(),"second.txt", sizeTwo));
        fs.getCurrentDirectory().add(new File(fs.getCurrentDirectory(),"third.txt", sizeThree));
        fs.getCurrentDirectory().add(new File(fs.getCurrentDirectory(),"fourth.txt", sizeFour));
        fs.getCurrentDirectory().add(new Directory("Not Empty", fs.getCurrentDirectory()));
        fs.changeDirectory("Not Empty");
        fs.getCurrentDirectory().add(new File(fs.getCurrentDirectory(),"fifth.txt", sizeFive));
        fs.getCurrentDirectory().add(new File(fs.getCurrentDirectory(),"sixth.txt", sizeSix));
        fs.changeBack();
        fs.changeBack();
        Assertions.assertTrue(fs.getCurrentDirectory().getName().equals("/"));
        System.out.println(fs.size());
        System.out.println(totalSize);
        Assertions.assertEquals(totalSize, fs.size());
    }

    @Test
    public void createdFileSystemFromLogEntryReturnsCorrectUpperboundSize()
    {
        LogFileParser parser = new LogFileParser("src/tests/resources/day7_test.txt");
        try
        {
            FileSystem fs = parser.createFileSystemFromLog();
            System.out.println(fs.getSizeOfAll(100000));
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void sizeCalculationOfTestInputDataIsCorrect()
    {
        LogFileParser parser = new LogFileParser("src/tests/resources/day7_test.txt");
        try
        {
            FileSystem fs = parser.createFileSystemFromLog();
            long testInputUsedSpaceReference = 48381165;
            Assertions.assertEquals(testInputUsedSpaceReference, fs.size());
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void freeDiskSpaceIsCalculatedCorrectlyWhenDirectoriesOrFilesAreAdded()
    {
        LogFileParser parser = new LogFileParser("src/tests/resources/day7_test.txt");
        try
        {
            FileSystem fs = parser.createFileSystemFromLog();
            long testInputFreeSpace = 21618835;
            Assertions.assertEquals(testInputFreeSpace, fs.getFreeDiskSpace());
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}

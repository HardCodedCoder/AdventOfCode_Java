package Day7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}

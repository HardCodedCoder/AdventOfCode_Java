package Day7;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;

public class Day7
{
    public static void main(String [] args) throws IOException
    {
        LogFileParser parser = new LogFileParser("src/main/resources/day7_input.txt");
        FileSystem fs = parser.createFileSystemFromLog();
        System.out.println(fs.getSizeOfAll(100000));
        System.out.println(fs.sizeCounter);
        Collections.sort(fs.sizes);
        System.out.println("Hello");
    }
}

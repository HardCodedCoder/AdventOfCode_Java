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
        fs.update(30000000);
        System.out.println("Hello");
    }
}

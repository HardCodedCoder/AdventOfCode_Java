package Day7;

import java.io.IOException;
import java.util.List;
import main.java.FileReader;

public class LogFileParser
{
    private List<String> logfileContent;

    /**
     * Initializes a new instance of the LogFileParser class.
     * @param filePath  The path which points to the logfile location.
     * @throws IllegalArgumentException If the passed path is null or empty or an IO Exception occurs.
     */
    public LogFileParser(String filePath) throws IllegalArgumentException
    {
        this.readLogfileContent(filePath);
    }

    private FileSystem createFileSystemFromLog()
    {
        FileSystem fs = new FileSystem();

        for (int lineIdx = 1; lineIdx < this.logfileContent.size(); lineIdx++)
        {
            String [] split = this.logfileContent.get(lineIdx).split(" ");

            if (split[0].equals("$"))
            {
                if (split[1] == "ls")
                    continue;
                else if (split[1].equals("cd"))
                {

                }
            }
            else if (split[0].equals("dir"))
            {
                fs.getCurrentDirectory().newDirectory(split[1]);
            }
        }

        return null;
    }

    /**
     * Reads the content of the logfile.
     * @param path The path which points to the logfile location.
     * @throws IllegalArgumentException If the passed path is null or empty or an IOException occurs.
     */
    private void readLogfileContent(String path) throws IllegalArgumentException
    {
        FileReader reader = new FileReader();
        try
        {
            this.logfileContent = reader.readFile(path);
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            throw new IllegalArgumentException("The provided path caused an IO Error. Try again later.!");
        }
    }
}

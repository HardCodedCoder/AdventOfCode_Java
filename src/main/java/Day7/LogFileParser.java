package Day7;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.rmi.UnexpectedException;
import java.text.MessageFormat;
import java.util.List;
import General.FileReader;

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

    public FileSystem createFileSystemFromLog() throws IOException
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
                    if (split[2].equals(""))
                        System.err.println("Error: Argument after cd is empty!");
                    else if (split[2].equals(".."))
                        fs.changeBack();
                    else
                        fs.changeDirectory(split[2]);
                }
            }
            else if (split[0].equals("dir"))
                fs.getCurrentDirectory().add(new Directory(split[1], fs.getCurrentDirectory()));
            else
            {
                int fileSize = Integer.parseInt(split[0]);
                fs.getCurrentDirectory().add(new File(fs.getCurrentDirectory(), split[1], fileSize));
            }
        }

        return fs;
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

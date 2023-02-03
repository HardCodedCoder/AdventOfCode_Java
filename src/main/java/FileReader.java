import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * A class which provides methods for reading the content of a
 * text based file.
 */
public class FileReader
{
    /**
     * Initializes a new instance of the FileReader class.
     */
    public FileReader()
    {

    }

    /**
     * Reads the content of a text base file into a List of type String.
     * @param path A String representing the path.
     * @return  A List of Strings representing each line of the file.
     * @throws IllegalArgumentException Will be thrown if the passed path is null or empty or the file doesn't exist.
     * @throws IOException if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read
     *                      SecurityException – In the case of the default provider, and a security manager is installed,
     *                      the checkRead method is invoked to check read access to the file.
     */
    public List<String> readFile(String path) throws IllegalArgumentException,
                                                        IOException
    {
        if (path == null || path.isEmpty())
            throw new IllegalArgumentException("The provided path mustn't be null or empty!");

        File file = new File(path);
        if (!file.exists())
            throw new IllegalArgumentException("The provided file does not exist!");

        List<String> fileContent = null;

        return Files.readAllLines(file.toPath());
    }
}

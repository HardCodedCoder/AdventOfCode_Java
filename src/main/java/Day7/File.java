package Day7;

public class File extends FileSystemEntry {

    /**
     * Holds the size of the file.
     */
    private int size;

    /**
     * Initializes a new instance of the File class.
     * @param parent The parent(-directory) of the file.
     * @param name The name of the file.
     * @param size The size of the file (in bytes).
     * @throws IllegalArgumentException if the passed name is null or empty.
     */
    public File(Directory parent, String name, int size) throws IllegalArgumentException
    {
        super(parent, name);
        setSize(size);
    }

    /**
     * Initializes a new instance of the File class.
     *
     * @param name The name of the File
     * @throws IllegalArgumentException If the passed name is null or empty.
     */
    public File(String name) throws IllegalArgumentException {
        this(null, name, 1);
    }

    /**
     * Gets if this File is a directory.
     *
     * @return True if this instance is a directory, otherwise false.
     */
    @Override
    public boolean isDirectory() {
        return false;
    }

    /**
     * Gets the size of the File.
     *
     * @return The size of the File.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Sets the size of the file.
     * @param size The size of the file to set.
     * @throws IllegalArgumentException If the passed size is lower than 1 byte.
     */
    public void setSize(int size) throws IllegalArgumentException {
        if (size < 1)
            throw new IllegalArgumentException("The size of the file must be at least 1 byte big.");

        this.size = size;
    }
}

package Day7;

public abstract class FileSystemEntry
{
    /**
     * Holds the parent of this entry.
     */
    private FileSystemEntry parent;

    /**
     * Holds the name of the FileSystemEntry.
     */
    private String name;

    /**
     * Initializes a new instance of the FileSystemEntry class.
     * @param name The name of the FileSystemEntry
     * @throws IllegalArgumentException If the passed name is null or empty.
     */
    public FileSystemEntry(String name) throws  IllegalArgumentException
    {
        this(null, name);
    }

    /**
     * Initializes a new instance of the FileSystemEntry class.
     * @param parent The parent(-directory) of this entry.
     * @param name The name of the FileSystemEntry.
     * @throws IllegalArgumentException If the passed name is null or empty.
     */
    public FileSystemEntry(FileSystemEntry parent, String name) throws IllegalArgumentException
    {
        this.parent = parent;
        this.setName(name);
    }

    /**
     * Gets the name of the FileSystemEntry.
     * @return The name of the FileSystemEntry.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the parent FileSystemEntry of this entry.
     * @return The parent of the FileSystemEntry.
     */
    public FileSystemEntry getParent()
    {
        return parent;
    }

    /**
     * Sets the name of the FileSystemEntry.
     * @param name The name of the FileSystemEntry.
     * @throws IllegalArgumentException If the passed name is null or empty.
     */
    public void setName(String name) throws IllegalArgumentException
    {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("The passed name of the FileSystemEntry mustn't be null!");

        this.name = name;
    }

    /**
     * Gets if this FileSystemEntry is a directory.
     * @return True if this instance is a directory, otherwise false.
     */
    public abstract boolean isDirectory();

    /**
     * Gets the size of the FileSystemEntry.
     *
     * @return The size of the FileSystemEntry.
     */
    public abstract int size();


    /**
     * Compares this Object with another object.
     * @param o The other object to compare with.
     * @return True if both objects are the same, otherwise false.
     */
    @Override
    public boolean equals(Object o)
    {
        return this.getName().equals(((FileSystemEntry)o).getName());
    }
}

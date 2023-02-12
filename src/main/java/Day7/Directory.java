package Day7;
import java.util.Set;

import main.java.Day7.FileSystemEntry;

public class Directory extends FileSystemEntry
{
    /**
     * Holds the child elements of this directory.
     */
    private Set<FileSystemEntry> entries;
    /**
     * Initializes a new instance of the Directory class.
     *
     * @param name The name of the FileSystemEntry
     * @throws IllegalArgumentException If the passed name is null or empty.
     */
    public Directory(String name) throws IllegalArgumentException {
        super(name);
    }

    /**
     * Creates a directory with some content.
     * @param name The name of the directory.
     * @param entries The content of the directory (files or other directories).
     * @throws IllegalArgumentException If the name of the directory is null or empty.
     */
    public Directory(String name, Set<FileSystemEntry> entries) throws IllegalArgumentException {
        super(null, name);
        this.entries = entries;
    }
    /**
     * Initializes a new instance of the Directory class.
     *
     * @param parent The parent(-directory) of this entry.
     * @param name   The name of the FileSystemEntry.
     * @throws IllegalArgumentException If the passed name is null or empty.
     */
    public Directory(FileSystemEntry parent, String name) throws IllegalArgumentException {
        super(parent, name);
    }

    /**
     * Gets if this FileSystemEntry is a directory.
     *
     * @return True if this instance is a directory, otherwise false.
     */
    @Override
    public boolean isDirectory() {
        return true;
    }

    /**
     * Gets the size of the FileSystemEntry.
     *
     * @return The size of the FileSystemEntry.
     */
    @Override
    public int size() {
        if (this.entries.isEmpty())
            return 0;

        int size = 0;
        for (FileSystemEntry entry : this.entries)
        {
            size += entry.size();
        }

        return size;
    }

    public void newDirectory(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("The name of the new directory mustn't be null or empty!");

        if (!this.entries.add(new Directory(name)))
            throw new IllegalArgumentException("A directory with the passed name already exists!");
    }

    /**
     * Generates a hashcode for the given object.
     * NOTE: This method is overridden to achieve that the HashSet
     *       is using the equals function to check for equality,
     *       as a HashMap would compare for objects hashcodes normally.
     *       To achieve this, the objects to compare with each other
     *       must return the same hashcode.
     *       This behaviour can impact the performance of a hashmap.
     * @return
     */
    @Override
    public int hashCode()
    {
        return 0;
    }

    public Set<FileSystemEntry> getContent()
    {
        return this.entries;
    }
}

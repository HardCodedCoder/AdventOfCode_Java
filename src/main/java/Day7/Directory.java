package Day7;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

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
        this.entries = new HashSet<>();
    }

    /**
     * Initializes a new instance of the Directory class.
     * @param name The name of the Directory
     * @param parent The parent Directory.
     */
    public Directory(String name, Directory parent)
    {
        super((FileSystemEntry)parent, name);
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

    /**
     * Checks whether the Directory contains a FileSystemEntry with the given name.
     * @param name  The name of the FileSystemEntry to check.
     * @return  True if a directory or file consisting of the passed name exists, otherwise false.
     */
    public boolean contains(String name)
    {
        for (FileSystemEntry entry : this.entries)
        {
            if (entry.getName().equals(name))
                return true;
        }

        return false;
    }

    /**
     * Adds a directory or a file to this directory.
     * @param entry The directory or file to add.
     * @return True if the directory or file was successfully added, otherwise false.
     */
    public boolean add(FileSystemEntry entry)
    {
        return this.entries.add(entry);
    }

    /**
     * Gets the child directory of this directory consisting of the passed name.
     * @param dirName The directory name of the directory to get.
     * @return The child directory.
     * @throws IllegalArgumentException Will be thrown if the passed name is null or empty.
     */
    public Directory getDirectory(String dirName) throws IllegalArgumentException
    {
        if (dirName == null || dirName.equals(""))
            throw new IllegalArgumentException("The passed dirName mustn't be null or empty");

        for (FileSystemEntry dir : this.entries)
        {
            if (dir.isDirectory() && dir.getName().equals(dirName))
                return (Directory)dir;
        }

        throw new IllegalArgumentException(MessageFormat.format("The passed directory %1 could not be found!", dirName));
    }

    /**
     * Checks whether this Directory is empty or not.
     * @return True if the Directory is empty, otherwise false.
     */
    public boolean isEmpty()
    {
        return this.entries.isEmpty();
    }


}

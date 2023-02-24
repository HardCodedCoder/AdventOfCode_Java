package Day7;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSystem
{

    private final long TOTAL_DISK_SIZE = 70000000;

    private Directory root;

    private Directory currentDirectory;

    private long sizeCounter = 0;

    private long freeDiskSpace = TOTAL_DISK_SIZE;

    private List<Long> allDirectorySizes = new ArrayList<>();

    public FileSystem()
    {
        this.root = new Directory("/");
        this.currentDirectory = this.root;
    }

    public Directory getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(Directory dir) {
        if (dir == null)
            throw new IllegalArgumentException("The passed directory must'nt be null!");

        this.currentDirectory = dir;
    }

    public void changeBack()
    {
        if (this.currentDirectory == this.root)
            return;
        else
            this.setCurrentDirectory((Directory)this.getCurrentDirectory().getParent());
    }

    public void addFileSystemEntry(FileSystemEntry entry)
    {
        if (this.currentDirectory.add(entry))
            this.freeDiskSpace -= entry.size();
    }

    public void changeDirectory(String dirName)
    {
        if (this.currentDirectory.contains(dirName))
            this.currentDirectory = this.currentDirectory.getDirectory(dirName);
        else
            System.err.println(MessageFormat.format("There is no directory named: %1", dirName));
    }

    public void changeToRootDir()
    {
        this.currentDirectory = this.root;
    }

    public long size() {
        return this.root.size();
    }

    public long getSizeOfAll(long upperBound) {

        for (FileSystemEntry entry : this.currentDirectory.getContent())
        {
            long tmp = entry.size();
            if (entry.isDirectory())
            {
                this.allDirectorySizes.add(tmp);
                if (tmp <= upperBound) {
                    sizeCounter += tmp;
                }
                Directory parent = (Directory)entry.getParent();
                this.setCurrentDirectory((Directory) entry);
                this.getSizeOfAll(upperBound);
                this.setCurrentDirectory(parent);
            }
        }

        return sizeCounter;
    }

    public long getFreeDiskSpace() {
        return freeDiskSpace;
    }

    private void clearSizeCounter() {
        this.sizeCounter = 0;
    }

    public void update(long updateSize)
    {
        if (updateSize > this.freeDiskSpace)
        {
            long additionalSpaceNeeded = updateSize - this.freeDiskSpace;

            this.getSizeOfAll(updateSize);
            Collections.sort(this.allDirectorySizes);
            this.allDirectorySizes.removeIf(element -> element < additionalSpaceNeeded);
            long toDelete = this.allDirectorySizes.get(0);
            System.out.format("Additional space freed: %d", toDelete);
        }

        this.addFileSystemEntry(new File(this.currentDirectory, "update", updateSize));
    }
}

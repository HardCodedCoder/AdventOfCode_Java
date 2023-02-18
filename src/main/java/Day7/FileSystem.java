package Day7;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class FileSystem
{
    private Directory root;

    private Directory currentDirectory;

    public long sizeCounter = 0;

    public List<Long> sizes = new ArrayList<>();
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
                if (tmp <= upperBound) {
                    sizeCounter += tmp;
                    this.sizes.add(tmp);
                }
                Directory parent = (Directory)entry.getParent();
                this.setCurrentDirectory((Directory) entry);
                this.getSizeOfAll(upperBound);
                this.setCurrentDirectory(parent);
            }
        }

        return sizeCounter;
    }
}

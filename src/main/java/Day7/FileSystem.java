package Day7;

import java.text.MessageFormat;

public class FileSystem
{
    private Directory root;

    private Directory currentDirectory;

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
}

package Day7;

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
}

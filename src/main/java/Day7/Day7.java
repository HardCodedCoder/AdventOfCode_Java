package Day7;

import main.java.FileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day7
{
    public static void main(String [] args)
    {
        Directory one = new Directory("Hallo");
        Directory two = new Directory("Hallo");
        Directory three = new Directory("Test");
        System.out.println(one.equals(two));
        HashSet<Directory> test = new HashSet<>();
        test.add(one);
        test.add(two);
        test.add(three);
        System.out.println(test.size());
    }
}

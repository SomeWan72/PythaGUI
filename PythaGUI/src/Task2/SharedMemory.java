package Task2;

import java.util.*;
import java.util.concurrent.Semaphore;

public class SharedMemory
{
    public Semaphore mutex = new Semaphore(1, true);
    public Semaphore semListA = new Semaphore(0, true);
    public Semaphore semListB = new Semaphore(0, true);

    public ArrayList<Integer> listA;
    public ArrayList<Integer> listB;

    public boolean onProcess;

    public SharedMemory()
    {
        listA = new ArrayList<>();
        listB = new ArrayList<>();
    }
}

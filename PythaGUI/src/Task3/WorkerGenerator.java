package Task3;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkerGenerator extends SwingWorker<ArrayList<Integer>, Integer> {
    private int numPairs;
    private int list;
    private Panel panel;
    private SharedMemory m;
    private int i = 1;

    public WorkerGenerator(int numPairs, int list, Panel panel, SharedMemory m)
    {
        this.numPairs = numPairs;
        this.list = list;
        this.panel = panel;
        this.m = m;
    }

    @Override
    public ArrayList<Integer> doInBackground() throws InterruptedException
    {
        Random r = new Random();
        int res;

        for(int i = 0; i < numPairs; i++)
        {
            res = r.nextInt(100);

            m.mutex.acquire();

            if(list == 0)
            {
                m.listA.add(res);
                m.semListA.release();
            }else
            {
                m.listB.add(res);
                m.semListB.release();
            }

            m.mutex.release();
            publish(res);
            Thread.sleep(50);
        }

        return null;
    }

    @Override
    public void process(List<Integer> chunk)
    {
        for(Integer in: chunk)
        {
            if(list == 0)
            {
                panel.setListA(listWriter(in));
            }else
            {
                panel.setListB(listWriter(in));
            }
        }
    }

    private String listWriter(Integer in)
    {
        String res = "(" + i + " -> " + in + ")\n";

        i++;

        return res;
    }
}

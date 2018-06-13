package Task2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class WorkerSearcher extends SwingWorker<ArrayList<String>, String>
{
    private int num;
    private Panel panel;
    private SharedMemory m;
    private int ctr = 0;

    public WorkerSearcher(int num, Panel panel, SharedMemory m)
    {
        this.num = num;
        this.panel = panel;
        this.m = m;
    }

    @Override
    protected ArrayList<String> doInBackground() throws InterruptedException
    {
        int numA, numB;
        double resNum;

        while(ctr < num)
        {
            m.semListA.acquire();
            m.semListB.acquire();

            m.mutex.acquire();

            numA = m.listA.get(ctr);
            numB = m.listB.get(ctr);

            m.mutex.release();

            resNum = sqrt(pow(numA,2) + pow(numB,2));


            if (floor(resNum) == resNum)
            {
                publish("TRIIIIPLE!!! " + numA + "^2 + " + numB + "^2 = " + (int) resNum);
            } else
            {
                publish(numA + "^2 + " + numB + "^2 = " + resNum);
            }

            ctr++;
            Thread.sleep(50);
        }

        return null;
    }

    @Override
    public void done()
    {
        m.onProcess = false;
    }


    @Override
    public void process(List<String> chunk)
    {
        for(String str: chunk)
        {
            panel.setResList(listWriter(str));
        }
    }

    private String listWriter(String str)
    {
        String res = ctr + ": " + str + "\n";

        return res;
    }
}
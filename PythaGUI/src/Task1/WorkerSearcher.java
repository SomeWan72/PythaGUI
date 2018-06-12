package Task1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static java.lang.Math.*;

public class WorkerSearcher extends SwingWorker<ArrayList<String>, Void>
{
    private ArrayList<Integer> listA, listB;
    private int length;
    private Panel panel;

    public WorkerSearcher(ArrayList<Integer> listA, ArrayList<Integer> listB, Panel panel)
    {
        this.listA = listA;
        this.listB = listB;
        length = listA.size();
        this.panel = panel;
    }

    @Override
    protected ArrayList<String> doInBackground()
    {
        ArrayList<String> res = new ArrayList<>();
        int numA, numB;
        double numC;

        for (int i = 0; i < length; i++)
        {
            numA = listA.get(i);
            numB = listB.get(i);
            numC = sqrt(pow(numA,2) + pow(numB,2));

            if (floor(numC) == numC)
            {
                res.add("TRIIIIPLE!!! " + numA + "^2 + " + numB + "^2 = " + (int) numC);
            } else
            {
                res.add(numA + "^2 + " + numB + "^2 = " + numC);
            }
        }

        return res;
    }

    @Override
    public void done()
    {
        try
        {
            panel.setResList(listWriter(get()));
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }catch(ExecutionException e)
        {
            e.printStackTrace();
        }
    }

    private String listWriter(ArrayList<String> stringList)
    {
        String res = "";
        int i = 1;

        for(String j: stringList)
        {
            res += i + ": " + j + "\n";
            i++;
        }

        return res;
    }
}

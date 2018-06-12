package Task1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class WorkerGenerator extends SwingWorker<ArrayList<Integer>, Void> {
    private int numPairs;
    private int list;
    private Panel panel;

    public WorkerGenerator(int numPairs, int list, Panel panel)
    {
        this.numPairs = numPairs;
        this.list = list;
        this.panel = panel;
    }

    @Override
    protected ArrayList<Integer> doInBackground()
    {
        ArrayList<Integer> res = new ArrayList<>();
        Random r = new Random();

        for(int i = 0; i < numPairs; i++)
        {
            res.add(r.nextInt(100));
        }

        return res;
    }

    @Override
    public void done()
    {
        try
        {
            if(list == 0)
            {
                panel.setListA(listWriter(get()));
            }else
            {
                panel.setListB(listWriter(get()));
            }
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }catch(ExecutionException e)
        {
            e.printStackTrace();
        }
    }

    private String listWriter(ArrayList<Integer> intList)
    {
        String res = "";
        int i = 1;

        for(int j: intList)
        {
            res += "(" + i + " -> " + j + ")\n";
            i++;
        }

        return res;
    }
}

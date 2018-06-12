package Task1;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Controller implements ActionListener
{
    private Panel panel;
    private ArrayList<Integer> listA, listB;
    private SwingWorker<ArrayList<Integer>, Void> swA, swB;
    private SwingWorker<ArrayList<String>, Void> swR;

    public Controller(Panel panel)
    {
        this.panel = panel;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("ready"))
        {
            int numPairs = panel.readNum();
            panel.clearLists();

            swA = new WorkerGenerator(numPairs, 0, panel);
            swB = new WorkerGenerator(numPairs, 1, panel);

            swA.execute();
            swB.execute();

            try
            {
                listA = swA.get();
                listB = swB.get();
            }catch(InterruptedException ee)
            {
                ee.printStackTrace();
            }catch(ExecutionException ee)
            {
                ee.printStackTrace();
            }

            swR = new WorkerSearcher(listA, listB, panel);
            swR.execute();
        }
    }
}

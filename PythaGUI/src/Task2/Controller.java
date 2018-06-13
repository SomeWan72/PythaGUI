package Task2;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Controller implements ActionListener
{
    private Panel panel;
    private SharedMemory m = new SharedMemory();
    private SwingWorker<ArrayList<Integer>, Integer> swA, swB;
    private SwingWorker<ArrayList<String>, String> swR;

    public Controller(Panel panel)
    {
        this.panel = panel;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("ready"))
        {
            if(m.onProcess)
            {
                return;
            }

            m.onProcess = true;
            int numPairs = panel.readNum();
            panel.clearLists();

            swA = new WorkerGenerator(numPairs, 0, panel, m);
            swB = new WorkerGenerator(numPairs, 1, panel, m);
            swR = new WorkerSearcher(numPairs, panel, m);

            swA.execute();
            swB.execute();
            swR.execute();
        }
    }
}

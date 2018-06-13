package Task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.lang.Integer.min;

public class Panel extends JPanel
{
    private JLabel upperLabel = new JLabel("Number of random pairs that would be generated:");
    private JLabel listALabel = new JLabel("Natural Number List A.");
    private JLabel listBLabel = new JLabel("Natural Number List B.");
    private JLabel lowerLabel = new JLabel("Validation of pythagorian triples.");

    private JTextField numPairs = new JTextField(3);
    private JTextArea listA = new JTextArea(10, 30);
    private JTextArea listB = new JTextArea(10, 30);
    private JTextArea resList = new JTextArea(10, 60);

    private JProgressBar pg = new JProgressBar(0, 100);

    public Panel()
    {
        this.setLayout(new BorderLayout());
        add(upperPanel(), BorderLayout.NORTH);
        add(midPanel(), BorderLayout.CENTER);
        add(lowerPanel(), BorderLayout.SOUTH);
    }

    public void Controller(ActionListener e)
    {
        numPairs.addActionListener(e);
        numPairs.setActionCommand("ready");
    }

    private JPanel upperPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(upperLabel);
        panel.add(numPairs);

        return panel;
    }

    private JPanel midPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(new JScrollPane(listA), BorderLayout.CENTER);
        leftPanel.add(listALabel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(new JScrollPane(listB), BorderLayout.CENTER);
        rightPanel.add(listBLabel, BorderLayout.SOUTH);

        panel.add(leftPanel);
        panel.add(rightPanel);

        return panel;
    }

    private JPanel lowerPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(resList), BorderLayout.CENTER);

        JPanel low = new JPanel();
        low.setLayout(new BorderLayout());
        low.add(lowerLabel, BorderLayout.NORTH);
        low.add(pg, BorderLayout.SOUTH);
        pg.setStringPainted(true);

        panel.add(low, BorderLayout.SOUTH);

        return panel;
    }

    public int readNum()
    {
        return Integer.parseInt(numPairs.getText());
    }

    public void setListA(String text)
    {
        listA.append(text);
    }

    public void setListB(String text)
    {
        listB.append(text);
    }

    public void setResList(String text)
    {
        resList.append(text);
    }

    public void clearLists()
    {
        listA.setText("");
        listB.setText("");
        resList.setText("");
    }

    public void setPG(int num)
    {
        pg.setValue(num);
    }
}
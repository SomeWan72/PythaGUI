package Task1;

import javax.swing.*;
import java.awt.*;

public class Launch
{
    public static void createGUI(JFrame window)
    {
        Panel panel = new Panel();
        Controller ctl = new Controller(panel);
        panel.Controller(ctl);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(700, 410));
        window.setContentPane(panel);
        window.setIconImage(new ImageIcon("pythagui.png").getImage());
        window.pack();
        window.setVisible(true);
    }

    public static void main(String[] args)
    {
        final JFrame window = new JFrame("PythaGUI");
        createGUI(window);

        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                createGUI(window);
            }
        });
    }
}

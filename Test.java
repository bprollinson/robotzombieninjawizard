import java.awt.*;
import javax.swing.*;

public class Test
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Robot Zombie Ninja Wizard");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(20, 20));
        frame.getContentPane().add(panel);

        JPanel innerPanel[][] = new JPanel[20][20];
        for (int i = 0; i < 20; i++)
        {
            for (int j = 0; j < 20; j++)
            {
                innerPanel[i][j] = new JPanel();
                JLabel label = new JLabel("X");
                innerPanel[i][j].add(label);
                panel.add(innerPanel[i][j]);
            }
        }

        frame.setVisible(true);
    }
}

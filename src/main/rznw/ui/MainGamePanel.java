package rznw.ui;

import rznw.map.Map;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainGamePanel extends JPanel
{
    public static final int NUM_SUMMARY_ROWS = 4;

    private JPanel[][] innerPanel;

    public MainGamePanel()
    {
        this.setLayout(new GridLayout(MainGameFrame.NUM_ROWS, MainGameFrame.NUM_COLUMNS));

        this.innerPanel = new JPanel[MainGameFrame.NUM_ROWS][MainGameFrame.NUM_COLUMNS];

        for (int i = 0; i < MainGameFrame.NUM_ROWS; i++)
        {
            for (int j = 0; j < Map.NUM_COLUMNS; j++)
            {
                JPanel panel = new JPanel();
                panel.add(new JLabel());

                this.add(panel);
                this.innerPanel[i][j] = panel;
            }
        }
    }

    public void renderDisplayString(int i, int j, String displayString)
    {
        for (int k = 0; k < displayString.length(); k++)
        {
            this.renderDisplayCharacter(i, j + k, displayString.charAt(k));
        }
    }

    public void renderDisplayCharacter(int i, int j, char displayCharacter)
    {
        JPanel panel = this.innerPanel[i][j];
        JLabel label = (JLabel)panel.getComponent(0);
        label.setText(displayCharacter + "");
    }
}

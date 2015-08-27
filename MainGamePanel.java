import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainGamePanel extends JPanel
{
    private static final int NUM_ROWS = 20;
    private static final int NUM_COLUMNS = 20;
    private JPanel innerPanel[][];

    public MainGamePanel()
    {
        this.setLayout(new GridLayout(MainGamePanel.NUM_ROWS, MainGamePanel.NUM_COLUMNS));

        this.innerPanel = new JPanel[MainGamePanel.NUM_ROWS][MainGamePanel.NUM_COLUMNS];

        for (int i = 0; i < MainGamePanel.NUM_ROWS; i++)
        {
            for (int j = 0; j < MainGamePanel.NUM_COLUMNS; j++)
            {
                JPanel panel = new JPanel();
                panel.add(new JLabel("X"));

                this.add(panel);
                this.innerPanel[i][j] = panel;
            }
        }
    }
}

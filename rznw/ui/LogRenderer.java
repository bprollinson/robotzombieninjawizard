package rznw.ui;

import rznw.map.Map;

import java.util.Vector;

public class LogRenderer
{
    private Vector<String> logs;
    private MainGameFrame frame;

    public LogRenderer(MainGameFrame frame)
    {
        this.logs = new Vector<String>();
        this.frame = frame;
    }

    public void log(String message)
    {
        this.logs.add(message);

        this.clearScreen();
        for (int i = 0; i < this.logs.size(); i++)
        {
            this.frame.renderDisplayString(Map.NUM_ROWS + MainGamePanel.NUM_SUMMARY_ROWS + i, 0, this.logs.get(i));
        }
    }

    private void clearScreen()
    {
        for (int row = Map.NUM_ROWS + MainGamePanel.NUM_SUMMARY_ROWS; row < MainGameFrame.NUM_ROWS; row++)
        {
            for (int column = 0; column < MainGameFrame.NUM_COLUMNS; column++)
            {
                this.frame.renderDisplayCharacter(row, column, ' ');
            }
        }
    }
}

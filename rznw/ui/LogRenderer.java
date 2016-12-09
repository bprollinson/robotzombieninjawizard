package rznw.ui;

import rznw.map.Map;
import rznw.utility.StringUtils;

import java.util.Vector;

public class LogRenderer
{
    public static final int NUM_ROWS = 8;

    private Vector<String> logs;
    private MainGameFrame frame;
    private StringUtils stringUtils;

    public LogRenderer(MainGameFrame frame)
    {
        this.logs = new Vector<String>();
        this.frame = frame;
        this.stringUtils = new StringUtils();
    }

    public void log(String message)
    {
        String[] logLines = this.stringUtils.splitIntoLines(message, MainGameFrame.NUM_COLUMNS);
        for (int i = 0; i < logLines.length; i++)
        {
            this.logs.add(logLines[i]);
        }

        this.render();
    }

    public void clear()
    {
        this.logs = new Vector<String>();
    }

    public void render()
    {
        this.clearScreen();

        int endIndex = this.logs.size() - 1;
        int startIndex = Math.max(endIndex - LogRenderer.NUM_ROWS + 1, 0);

        for (int i = startIndex; i <= endIndex; i++)
        {
            this.frame.renderDisplayString(Map.NUM_ROWS + MainGamePanel.NUM_SUMMARY_ROWS + i - startIndex, 0, this.logs.get(i));
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

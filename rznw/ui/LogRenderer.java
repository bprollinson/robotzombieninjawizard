package rznw.ui;

import rznw.map.Map;

public class LogRenderer
{
    private MainGameFrame frame;

    public LogRenderer(MainGameFrame frame)
    {
        this.frame = frame;
    }

    public void log(String message)
    {
        this.frame.renderDisplayString(Map.NUM_ROWS + MainGamePanel.NUM_SUMMARY_ROWS, 0, message);
    }
}

package rznw.ui;

import java.util.Vector;

public class LogsScreenRenderer extends MenuScreenRenderer
{
    public static final int LOGS_PER_PAGE = 24;

    private MainGameFrame frame;

    public LogsScreenRenderer(MainGameFrame frame)
    {
        super(frame);

        this.frame = frame;
    }

    public void render(MenuState state, Vector<String> lines)
    {
        this.clearScreen();

        this.renderCenteredString(1, "Logs");

        this.frame.renderDisplayString(28, 0, "Page " + (state.getEntryNumber() + 1) + " / " + state.getNumEntries());

        int startIndex = state.getEntryNumber() * LogsScreenRenderer.LOGS_PER_PAGE;
        int endIndex = Math.min(startIndex + LogsScreenRenderer.LOGS_PER_PAGE - 1, lines.size() - 1);

        int row = 3;
        for (int i = startIndex; i <= endIndex; i++)
        {
            this.frame.renderDisplayString(row, 0, lines.get(i));
            row++;
        }

        if (state.getEntryNumber() > 0)
        {
            this.frame.renderDisplayString(30, 0, "Up for more");
        }

        if (state.getEntryNumber() < state.getNumEntries() - 1)
        {
            this.frame.renderDisplayString(31, 0, "Down for more");
        }
    }
}

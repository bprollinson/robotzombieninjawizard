package rznw.ui;

import rznw.utility.StringUtils;

public abstract class MenuScreenRenderer
{
    protected MainGameFrame frame;

    public MenuScreenRenderer(MainGameFrame frame)
    {
        this.frame = frame;
    }

    protected void clearScreen()
    {
        for (int i = 0; i < MainGameFrame.NUM_ROWS; i++)
        {
            for (int j = 0; j < MainGameFrame.NUM_COLUMNS; j++)
            {
                this.frame.renderDisplayCharacter(i, j, ' ');
            }
        }
    }

    protected void renderCenteredString(int row, String string)
    {
        int column = (MainGameFrame.NUM_COLUMNS - string.length()) / 2;
        this.frame.renderDisplayString(row, column, string);
    }

    protected int renderStringWithNewlines(int row, String string)
    {
        String[] lines = new StringUtils().splitIntoLines(string, MainGameFrame.NUM_COLUMNS);

        for (int i = 0; i < lines.length; i++)
        {
            this.frame.renderDisplayString(row + i, 0, lines[i]);
        }

        return lines.length;
    }
}

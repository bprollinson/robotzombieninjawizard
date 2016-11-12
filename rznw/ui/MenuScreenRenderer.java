package rznw.ui;

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
        String remainingString = string;

        int numLines = 1;

        while (remainingString.length() > MainGameFrame.NUM_COLUMNS)
        {
            String searchString = remainingString.substring(0, MainGameFrame.NUM_COLUMNS);
            int lastSpacePos = searchString.lastIndexOf(' ');
            String rowString = searchString.substring(0, lastSpacePos);
            this.frame.renderDisplayString(row, 0, rowString);

            remainingString = remainingString.substring(lastSpacePos + 1);
            row++;
            numLines++;
        }

        this.frame.renderDisplayString(row, 0, remainingString);

        return numLines;
    }
}

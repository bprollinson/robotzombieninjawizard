package rznw.ui;

public abstract class MenuScreenRenderer
{
    private static final int NUM_ROWS = 32;
    private static final int NUM_COLUMNS = 40;

    protected MainGameFrame frame;

    public MenuScreenRenderer(MainGameFrame frame)
    {
        this.frame = frame;
    }

    protected void clearScreen()
    {
        for (int i = 0; i < MenuScreenRenderer.NUM_ROWS; i++)
        {
            for (int j = 0; j < MenuScreenRenderer.NUM_COLUMNS; j++)
            {
                this.frame.renderDisplayCharacter(i, j, ' ');
            }
        }
    }

    protected void renderCenteredString(int row, String string)
    {
        int column = (MainMenuRenderer.NUM_COLUMNS - string.length()) / 2;
        this.frame.renderDisplayString(row, column, string);
    }

    protected int renderStringWithNewlines(int row, String string)
    {
        String remainingString = string;

        int numLines = 1;

        while (remainingString.length() > MenuScreenRenderer.NUM_COLUMNS)
        {
            String searchString = remainingString.substring(0, MenuScreenRenderer.NUM_COLUMNS);
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

package rznw.ui;

public abstract class MenuScreenRenderer
{
    private static final int NUM_ROWS = 32;
    private static final int NUM_COLUMNS = 40;

    private MainGameFrame frame;

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
}

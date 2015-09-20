package rznw.ui;

public class MainMenuRenderer
{
    public static final int NUM_ROWS = 32;
    public static final int NUM_COLUMNS = 40;

    private MainGameFrame frame;

    public MainMenuRenderer(MainGameFrame frame)
    {
        this.frame = frame;
    }

    public void render()
    {
        for (int i = 0; i < MainMenuRenderer.NUM_ROWS; i++)
        {
            for (int j = 0; j < MainMenuRenderer.NUM_COLUMNS; j++)
            {
                this.frame.renderDisplayCharacter(i, j, ' ');
            }
        }

        this.renderCenteredString(1, "Robot Zombie Ninja Wizard");

        this.renderCenteredString(4, "Character");
        this.renderCenteredString(6, "Skills");
        this.renderCenteredString(8, "Spells");
        this.renderCenteredString(10, "Inventory");
        this.renderCenteredString(12, "Save Game");
        this.renderCenteredString(14, "Load Game");
        this.renderCenteredString(16, "New Game");
        this.renderCenteredString(18, "Exit");
    }

    private void renderCenteredString(int row, String string)
    {
        int column = (MainMenuRenderer.NUM_COLUMNS - string.length()) / 2;
        this.frame.renderDisplayString(row, column, string);
    }
}

package rznw.ui;

public class MainMenuRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 4;
    private static final int MENU_ROW_HEIGHT = 2;

    private MainGameFrame frame;

    public MainMenuRenderer(MainGameFrame frame)
    {
        this.frame = frame;
    }

    public void render(MenuState state)
    {
        for (int i = 0; i < MainGameFrame.NUM_ROWS; i++)
        {
            for (int j = 0; j < MainGameFrame.NUM_COLUMNS; j++)
            {
                this.frame.renderDisplayCharacter(i, j, ' ');
            }
        }

        this.renderCenteredString(1, "Robot Zombie Ninja Wizard");

        this.renderCenteredString(4, "Resume");
        this.renderCenteredString(6, "Character");
        this.renderCenteredString(8, "Skills");
        this.renderCenteredString(10, "Spells");
        this.renderCenteredString(12, "Inventory");
        this.renderCenteredString(14, "Equipment");
        this.renderCenteredString(16, "Logs");
        this.renderCenteredString(18, "Instructions");
        this.renderCenteredString(20, "Save Game");
        this.renderCenteredString(22, "Load Game");
        this.renderCenteredString(24, "New Game");
        this.renderCenteredString(26, "Exit");

        this.renderCursor(state);
    }

    private void renderCenteredString(int row, String string)
    {
        int column = (MainGameFrame.NUM_COLUMNS - string.length()) / 2;
        this.frame.renderDisplayString(row, column, string);
    }

    private void renderCursor(MenuState state)
    {
        int row = MainMenuRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * MainMenuRenderer.MENU_ROW_HEIGHT;

        this.frame.renderDisplayCharacter(row, 12, 'X');
    }
}

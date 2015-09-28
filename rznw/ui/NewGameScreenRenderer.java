package rznw.ui;

public class NewGameScreenRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 5;
    private static final int MENU_ROW_HEIGHT = 2;

    public NewGameScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state)
    {
        this.clearScreen();
        this.renderCenteredString(1, "New Game");
        this.renderCenteredString(3, "Select a character class");
        this.renderCenteredString(5, "Robot");
        this.renderCenteredString(7, "Zombie");
        this.renderCenteredString(9, "Ninja");
        this.renderCenteredString(11, "Wizard");

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        int row = NewGameScreenRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * NewGameScreenRenderer.MENU_ROW_HEIGHT;

        this.frame.renderDisplayCharacter(row, 16, 'X');
    }
}

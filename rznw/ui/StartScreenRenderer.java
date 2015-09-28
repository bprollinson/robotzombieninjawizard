package rznw.ui;

public class StartScreenRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 4;
    private static final int MENU_ROW_HEIGHT = 2;

    public StartScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state)
    {
        this.clearScreen();

        this.renderCenteredString(1, "Robot Zombie Ninja Wizard");
        this.renderCenteredString(4, "Load Game");
        this.renderCenteredString(6, "New Game");
        this.renderCenteredString(8, "Exit");

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        int row = StartScreenRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * StartScreenRenderer.MENU_ROW_HEIGHT;

        this.frame.renderDisplayCharacter(row, 13, 'X');
    }
}

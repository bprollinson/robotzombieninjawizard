package rznw.ui;

public class ExitScreenRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 5;
    private static final int MENU_ROW_HEIGHT = 1;

    public ExitScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Exit Game");
        this.renderCenteredString(3, "Are you sure?");
        this.renderCenteredString(5, "Yes");
        this.renderCenteredString(6, "No");

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        int row = ExitScreenRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * ExitScreenRenderer.MENU_ROW_HEIGHT;

        this.frame.renderDisplayCharacter(row, 16, 'X');
    }
}

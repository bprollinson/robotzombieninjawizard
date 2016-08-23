package rznw.ui;

public class NewGameTypeScreenRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 3;
    private static final int MENU_ROW_HEIGHT = 2;

    public NewGameTypeScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state)
    {
        this.clearScreen();
        this.renderCenteredString(1, "New Game Type");
        this.renderCenteredString(3, "New Game");
        this.renderCenteredString(5, "New Game Plus");

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        int row = NewGameTypeScreenRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * NewGameTypeScreenRenderer.MENU_ROW_HEIGHT;

        this.frame.renderDisplayCharacter(row, 11, 'X');
    }
}

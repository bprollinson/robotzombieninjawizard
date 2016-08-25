package rznw.ui;

public class NewGamePlusScreenRenderer extends MenuScreenRenderer
{
    public NewGamePlusScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state)
    {
        this.clearScreen();
        this.renderCenteredString(1, "New Game Plus");

        for (int i = 0; i < 10; i++)
        {
            this.renderCenteredString(4 + 2 * i, "Slot " + (i + 1));
        }

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        this.frame.renderDisplayCharacter(4 + 2 * state.getEntryNumber(), 14, 'X');
    }
}

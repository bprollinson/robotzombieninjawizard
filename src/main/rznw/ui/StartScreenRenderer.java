package rznw.ui;

import rznw.Version;

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

        this.renderCenteredString(36, "Version " + Version.getVersionString());

        this.renderCenteredString(38, "Copyright");
        this.renderCenteredString(39, "Brendan Rollinson-Lorimer");
        this.renderCenteredString(40, "2015");

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        int row = StartScreenRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * StartScreenRenderer.MENU_ROW_HEIGHT;

        this.frame.renderDisplayCharacter(row, 13, 'X');
    }
}

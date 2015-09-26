package rznw.ui;

public class LevelUpStatsMenuRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 6;
    private static final int MENU_ROW_HEIGHT = 1;

    public LevelUpStatsMenuRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state, int numPoints)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Level Up!");
        this.renderCenteredString(3, "Stat points remaining: " + numPoints);

        this.frame.renderDisplayString(5, 2, "Major Stat 1");
        this.frame.renderDisplayString(6, 2, "Minor Stat 1.1");
        this.frame.renderDisplayString(7, 2, "Minor Stat 1.2");
        this.frame.renderDisplayString(8, 2, "Minor Stat 1.3");
        this.frame.renderDisplayString(9, 2, "Minor Stat 1.4");

        this.frame.renderDisplayString(11, 2, "Major Stat 2");
        this.frame.renderDisplayString(12, 2, "Minor Stat 2.1");
        this.frame.renderDisplayString(13, 2, "Minor Stat 2.2");
        this.frame.renderDisplayString(14, 2, "Minor Stat 2.3");
        this.frame.renderDisplayString(15, 2, "Minor Stat 2.4");

        this.frame.renderDisplayString(17, 2, "Major Stat 3");
        this.frame.renderDisplayString(18, 2, "Minor Stat 3.1");
        this.frame.renderDisplayString(19, 2, "Minor Stat 3.2");
        this.frame.renderDisplayString(20, 2, "Minor Stat 3.3");
        this.frame.renderDisplayString(21, 2, "Minor Stat 3.4");

        this.frame.renderDisplayString(23, 2, "Major Stat 4");
        this.frame.renderDisplayString(24, 2, "Minor Stat 4.1");
        this.frame.renderDisplayString(25, 2, "Minor Stat 4.2");
        this.frame.renderDisplayString(26, 2, "Minor Stat 4.3");
        this.frame.renderDisplayString(27, 2, "Minor Stat 4.4");

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        int rowSpaces = 2 * (state.getEntryNumber() / 4);
        int row = LevelUpStatsMenuRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * LevelUpStatsMenuRenderer.MENU_ROW_HEIGHT + rowSpaces;

        this.frame.renderDisplayCharacter(row, 0, 'X');
    }
}

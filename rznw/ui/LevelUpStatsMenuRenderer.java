package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

public class LevelUpStatsMenuRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 6;
    private static final int MENU_ROW_HEIGHT = 1;

    public LevelUpStatsMenuRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state, int numPoints, MainCharacter mainCharacter)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Level Up!");
        this.renderCenteredString(3, "Stat points remaining: " + numPoints);

        this.renderPointGroup(mainCharacter, 0, 5);
        this.renderPointGroup(mainCharacter, 1, 11);
        this.renderPointGroup(mainCharacter, 2, 17);
        this.renderPointGroup(mainCharacter, 3, 23);

        this.renderCursor(state);
    }

    private void renderPointGroup(MainCharacter mainCharacter, int groupNumber, int startRow)
    {
        int groupDisplay = groupNumber + 1;
        this.frame.renderDisplayString(startRow, 2, "Major Stat " + groupDisplay);

        for (int i = 0; i < 4; i++)
        {
            int groupPositionDisplay = i + 1;
            int pointIndex = groupNumber * 4 + i;
            this.frame.renderDisplayString(startRow + i + 1, 2, "Minor Stat " + groupDisplay + "." + groupPositionDisplay + ": " + mainCharacter.getStatPoints(pointIndex));
        }
    }

    private void renderCursor(MenuState state)
    {
        int rowSpaces = 2 * (state.getEntryNumber() / 4);
        int row = LevelUpStatsMenuRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * LevelUpStatsMenuRenderer.MENU_ROW_HEIGHT + rowSpaces;

        this.frame.renderDisplayCharacter(row, 0, 'X');
    }
}

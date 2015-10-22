package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

public class SkillsScreenRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 4;
    private static final int MENU_ROW_HEIGHT = 1;

    public SkillsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state, MainCharacter mainCharacter)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Skills");

        this.renderPointGroup(mainCharacter, 0, 3);
        this.renderPointGroup(mainCharacter, 1, 9);
        this.renderPointGroup(mainCharacter, 2, 15);
        this.renderPointGroup(mainCharacter, 3, 21);

        this.renderCursor(state);
    }

    private void renderPointGroup(MainCharacter mainCharacter, int groupNumber, int startRow)
    {
        String groupDisplay = MainCharacter.getSkillCategory(groupNumber);
        this.frame.renderDisplayString(startRow, 2, groupDisplay);

        for (int i = 0; i < 4; i++)
        {
            int pointIndex = groupNumber * 4 + i;
            this.frame.renderDisplayString(startRow + i + 1, 2, MainCharacter.getSkillName(pointIndex) + ": " + mainCharacter.getSkillPoints(pointIndex));
        }
    }

    private void renderCursor(MenuState state)
    {
        int rowSpaces = 2 * (state.getEntryNumber() / 4);
        int row = SkillsScreenRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * SkillsScreenRenderer.MENU_ROW_HEIGHT + rowSpaces;

        this.frame.renderDisplayCharacter(row, 0, 'X');
    }
}

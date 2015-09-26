package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

public class LevelUpSkillsMenuRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 6;
    private static final int MENU_ROW_HEIGHT = 1;

    public LevelUpSkillsMenuRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state, int numPoints, MainCharacter mainCharacter)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Level Up!");
        this.renderCenteredString(3, "Skill points remaining: " + numPoints);

        this.frame.renderDisplayString(5, 2, "Major Skill 1");
        this.frame.renderDisplayString(6, 2, "Minor Skill 1.1: " + mainCharacter.getSkillPoints(0));
        this.frame.renderDisplayString(7, 2, "Minor Skill 1.2: " + mainCharacter.getSkillPoints(1));
        this.frame.renderDisplayString(8, 2, "Minor Skill 1.3: " + mainCharacter.getSkillPoints(2));
        this.frame.renderDisplayString(9, 2, "Minor Skill 1.4: " + mainCharacter.getSkillPoints(3));

        this.frame.renderDisplayString(11, 2, "Major Skill 2");
        this.frame.renderDisplayString(12, 2, "Minor Skill 2.1: " + mainCharacter.getSkillPoints(4));
        this.frame.renderDisplayString(13, 2, "Minor Skill 2.2: " + mainCharacter.getSkillPoints(5));
        this.frame.renderDisplayString(14, 2, "Minor Skill 2.3: " + mainCharacter.getSkillPoints(6));
        this.frame.renderDisplayString(15, 2, "Minor Skill 2.4: " + mainCharacter.getSkillPoints(7));

        this.frame.renderDisplayString(17, 2, "Major Skill 3");
        this.frame.renderDisplayString(18, 2, "Minor Skill 3.1: " + mainCharacter.getSkillPoints(8));
        this.frame.renderDisplayString(19, 2, "Minor Skill 3.2: " + mainCharacter.getSkillPoints(9));
        this.frame.renderDisplayString(20, 2, "Minor Skill 3.3: " + mainCharacter.getSkillPoints(10));
        this.frame.renderDisplayString(21, 2, "Minor Skill 3.4: " + mainCharacter.getSkillPoints(11));

        this.frame.renderDisplayString(23, 2, "Major Skill 4");
        this.frame.renderDisplayString(24, 2, "Minor Skill 4.1: " + mainCharacter.getSkillPoints(12));
        this.frame.renderDisplayString(25, 2, "Minor Skill 4.2: " + mainCharacter.getSkillPoints(13));
        this.frame.renderDisplayString(26, 2, "Minor Skill 4.3: " + mainCharacter.getSkillPoints(14));
        this.frame.renderDisplayString(27, 2, "Minor Skill 4.4: " + mainCharacter.getSkillPoints(15));

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        int rowSpaces = 2 * (state.getEntryNumber() / 4);
        int row = LevelUpSkillsMenuRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * LevelUpSkillsMenuRenderer.MENU_ROW_HEIGHT + rowSpaces;

        this.frame.renderDisplayCharacter(row, 0, 'X');
    }
}

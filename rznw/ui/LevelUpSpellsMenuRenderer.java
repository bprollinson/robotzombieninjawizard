package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

public class LevelUpSpellsMenuRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 6;
    private static final int MENU_ROW_HEIGHT = 1;

    public LevelUpSpellsMenuRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state, int numPoints, MainCharacter mainCharacter)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Level Up!");
        this.renderCenteredString(3, "Spell points remaining: " + numPoints);

        this.frame.renderDisplayString(5, 2, "Major Spell 1");
        this.frame.renderDisplayString(6, 2, "Minor Spell 1.1: " + mainCharacter.getSpellPoints(0));
        this.frame.renderDisplayString(7, 2, "Minor Spell 1.2: " + mainCharacter.getSpellPoints(1));
        this.frame.renderDisplayString(8, 2, "Minor Spell 1.3: " + mainCharacter.getSpellPoints(2));
        this.frame.renderDisplayString(9, 2, "Minor Spell 1.4: " + mainCharacter.getSpellPoints(3));

        this.frame.renderDisplayString(11, 2, "Major Spell 2");
        this.frame.renderDisplayString(12, 2, "Minor Spell 2.1: " + mainCharacter.getSpellPoints(4));
        this.frame.renderDisplayString(13, 2, "Minor Spell 2.2: " + mainCharacter.getSpellPoints(5));
        this.frame.renderDisplayString(14, 2, "Minor Spell 2.3: " + mainCharacter.getSpellPoints(6));
        this.frame.renderDisplayString(15, 2, "Minor Spell 2.4: " + mainCharacter.getSpellPoints(7));

        this.frame.renderDisplayString(17, 2, "Major Spell 3");
        this.frame.renderDisplayString(18, 2, "Minor Spell 3.1: " + mainCharacter.getSpellPoints(8));
        this.frame.renderDisplayString(19, 2, "Minor Spell 3.2: " + mainCharacter.getSpellPoints(9));
        this.frame.renderDisplayString(20, 2, "Minor Spell 3.3: " + mainCharacter.getSpellPoints(10));
        this.frame.renderDisplayString(21, 2, "Minor Spell 3.4: " + mainCharacter.getSpellPoints(11));

        this.frame.renderDisplayString(23, 2, "Major Spell 4");
        this.frame.renderDisplayString(24, 2, "Minor Spell 4.1: " + mainCharacter.getSpellPoints(12));
        this.frame.renderDisplayString(25, 2, "Minor Spell 4.2: " + mainCharacter.getSpellPoints(13));
        this.frame.renderDisplayString(26, 2, "Minor Spell 4.3: " + mainCharacter.getSpellPoints(14));
        this.frame.renderDisplayString(27, 2, "Minor Spell 4.4: " + mainCharacter.getSpellPoints(15));

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        int rowSpaces = 2 * (state.getEntryNumber() / 4);
        int row = LevelUpSpellsMenuRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * LevelUpSpellsMenuRenderer.MENU_ROW_HEIGHT + rowSpaces;

        this.frame.renderDisplayCharacter(row, 0, 'X');
    }
}

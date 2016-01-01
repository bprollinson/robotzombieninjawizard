package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;

public class LevelUpSpellsMenuRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 6;
    private static final int MENU_ROW_HEIGHT = 1;

    public LevelUpSpellsMenuRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state, int numPoints, MainCharacter mainCharacter, boolean showingDescription)
    {
        this.clearScreen();

        if (showingDescription)
        {
            this.renderCenteredString(1, mainCharacter.getSpellName(state.getEntryNumber()));

            int row = 3;

            row += this.renderStringWithNewlines(3, mainCharacter.getSpellDescription(state.getEntryNumber()));
            row++;

            int spellPoints = mainCharacter.getSpellPoints(state.getEntryNumber());
            SpellFactory factory = mainCharacter.getSpellFactory();
            Spell spell = factory.getSpell(state.getEntryNumber());

            if (spell != null)
            {
                if (spellPoints > 0)
                {
                    this.frame.renderDisplayString(row, 0, "Current level:");
                    row++;

                    String[] currentLevelStats = spell.getStats(mainCharacter, spellPoints);
                    for (int i = 0; i < currentLevelStats.length; i++)
                    {
                        this.frame.renderDisplayString(row + i, 0, currentLevelStats[i]);
                    }

                    row += currentLevelStats.length + 1;
                }

                this.frame.renderDisplayString(row, 0, "Next level:");
                row++;

                String[] nextLevelStats = spell.getStats(mainCharacter, spellPoints + 1);
                for (int i = 0; i < nextLevelStats.length; i++)
                {
                    this.frame.renderDisplayString(row + i, 0, nextLevelStats[i]);
                }
            }

            this.renderCenteredString(30, "Press 'i' to return to the spell menu");
        }
        else
        {
            this.renderCenteredString(1, "Level Up!");
            this.renderCenteredString(3, "Spell points remaining: " + numPoints);

            this.renderPointGroup(mainCharacter, 0, 5);
            this.renderPointGroup(mainCharacter, 1, 11);
            this.renderPointGroup(mainCharacter, 2, 17);
            this.renderPointGroup(mainCharacter, 3, 23);

            this.renderCenteredString(30, "Press 'i' for spell information");

            this.renderCursor(state);
        }
    }

    private void renderPointGroup(MainCharacter mainCharacter, int groupNumber, int startRow)
    {
        String groupDisplay = mainCharacter.getSpellCategory(groupNumber);
        this.frame.renderDisplayString(startRow, 2, groupDisplay);

        for (int i = 0; i < 4; i++)
        {
            int groupPositionDisplay = i + 1;
            int pointIndex = groupNumber * 4 + i;
            this.frame.renderDisplayString(startRow + i + 1, 2, mainCharacter.getSpellName(pointIndex) + ": " + mainCharacter.getSpellPoints(pointIndex));
        }
    }

    private void renderCursor(MenuState state)
    {
        int rowSpaces = 2 * (state.getEntryNumber() / 4);
        int row = LevelUpSpellsMenuRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * LevelUpSpellsMenuRenderer.MENU_ROW_HEIGHT + rowSpaces;

        this.frame.renderDisplayCharacter(row, 0, 'X');
    }
}

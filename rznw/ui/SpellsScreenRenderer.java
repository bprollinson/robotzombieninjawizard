package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;

public class SpellsScreenRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 4;
    private static final int MENU_ROW_HEIGHT = 1;

    public SpellsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state, MainCharacter mainCharacter, boolean showingDescription)
    {
        this.clearScreen();

        if (showingDescription)
        {
            this.renderCenteredString(1, mainCharacter.getSpells().getSpellName(state.getEntryNumber()));

            int row = 3;

            row += this.renderStringWithNewlines(3, mainCharacter.getSpells().getSpellDescription(state.getEntryNumber()));
            row++;

            int spellPoints = mainCharacter.getSpells().getSpellPoints(state.getEntryNumber());
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
            this.renderCenteredString(1, "Spells");

            this.renderPointGroup(mainCharacter, 0, 3);
            this.renderPointGroup(mainCharacter, 1, 9);
            this.renderPointGroup(mainCharacter, 2, 15);
            this.renderPointGroup(mainCharacter, 3, 21);

            this.renderCenteredString(30, "Press 'i' for spell information");

            this.renderCursor(state);
        }
    }

    public void renderDirectionInstructions()
    {
        this.renderCenteredString(30, "Press up, down, left or right to direct");
        this.renderCenteredString(31, "your spell.");
    }

    private void renderPointGroup(MainCharacter mainCharacter, int groupNumber, int startRow)
    {
        String groupDisplay = mainCharacter.getSpellCategory(groupNumber);
        this.frame.renderDisplayString(startRow, 2, groupDisplay);

        for (int i = 0; i < 4; i++)
        {
            int groupPositionDisplay = i + 1;
            int pointIndex = groupNumber * 4 + i;

            int basePoints = mainCharacter.getSpells().getSpellPoints(pointIndex, false);
            int points = mainCharacter.getSpells().getSpellPoints(pointIndex);

            String pointsDisplay = "" + basePoints;
            if (points != basePoints)
            {
                pointsDisplay += " -> " + points;
            }

            this.frame.renderDisplayString(startRow + i + 1, 2, mainCharacter.getSpells().getSpellName(pointIndex) + ": " + pointsDisplay);
        }
    }

    private void renderCursor(MenuState state)
    {
        int rowSpaces = 2 * (state.getEntryNumber() / 4);
        int row = SpellsScreenRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * SpellsScreenRenderer.MENU_ROW_HEIGHT + rowSpaces;

        this.frame.renderDisplayCharacter(row, 0, 'X');
    }
}

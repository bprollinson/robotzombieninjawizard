package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.MainCharacterStats;
import rznw.game.stat.Stat;
import rznw.game.stat.StatFactory;

public class LevelUpStatsMenuRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 6;
    private static final int MENU_ROW_HEIGHT = 1;

    public LevelUpStatsMenuRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state, int numPoints, MainCharacter mainCharacter, boolean showingDescription)
    {
        this.clearScreen();

        if (showingDescription)
        {
            this.renderCenteredString(1, mainCharacter.getStats().getStatName(state.getEntryNumber()));

            int row = 3;

            row += this.renderStringWithNewlines(3, mainCharacter.getStats().getStatDescription(state.getEntryNumber()));
            row++;

            int statPoints = mainCharacter.getStats().getStatPoints(state.getEntryNumber());
            StatFactory factory = mainCharacter.getStats().getStatFactory();
            Stat stat = factory.getStat(state.getEntryNumber());

            if (stat != null)
            {
                this.frame.renderDisplayString(row, 0, "Current level:");
                row++;

                String[] currentLevelStats = stat.getStats(mainCharacter, statPoints);
                for (int i = 0; i < currentLevelStats.length; i++)
                {
                    this.frame.renderDisplayString(row + i, 0, currentLevelStats[i]);
                }

                row += currentLevelStats.length + 1;

                this.frame.renderDisplayString(row, 0, "Next level:");
                row++;

                String[] nextLevelStats = stat.getStats(mainCharacter, statPoints + 1);
                for (int i = 0; i < nextLevelStats.length; i++)
                {
                    this.frame.renderDisplayString(row + i, 0, nextLevelStats[i]);
                }
            }

            this.renderCenteredString(40, "Press 'i' to return to the stat menu");
        }
        else
        {
            this.renderCenteredString(1, "Level Up!");
            this.renderCenteredString(3, "Stat points remaining: " + numPoints);

            this.renderPointGroup(mainCharacter, 0, 5);
            this.renderPointGroup(mainCharacter, 1, 11);
            this.renderPointGroup(mainCharacter, 2, 17);
            this.renderPointGroup(mainCharacter, 3, 23);

            this.renderStatDescription(mainCharacter, state);
            this.renderCenteredString(40, "Press 'i' for stat information");

            this.renderCursor(state);
        }
    }

    private void renderPointGroup(MainCharacter mainCharacter, int groupNumber, int startRow)
    {
        String groupDisplay = MainCharacterStats.getStatCategory(groupNumber);
        this.frame.renderDisplayString(startRow, 2, groupDisplay);

        for (int i = 0; i < 4; i++)
        {
            int pointIndex = groupNumber * 4 + i;
            this.frame.renderDisplayString(startRow + i + 1, 2, MainCharacterStats.getStatName(pointIndex) + ": " + mainCharacter.getStats().getStatPoints(pointIndex));
        }
    }

    private void renderStatDescription(MainCharacter mainCharacter, MenuState state)
    {
        this.frame.renderDisplayString(30, 0, "Description:");

        String statDescription = mainCharacter.getStats().getStatDescription(state.getEntryNumber());
        this.renderStringWithNewlines(32, statDescription);
    }

    private void renderCursor(MenuState state)
    {
        int rowSpaces = 2 * (state.getEntryNumber() / 4);
        int row = LevelUpStatsMenuRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * LevelUpStatsMenuRenderer.MENU_ROW_HEIGHT + rowSpaces;

        this.frame.renderDisplayCharacter(row, 0, 'X');
    }
}

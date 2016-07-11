package rznw.ui;

import rznw.game.maincharacter.ExperienceCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.stat.Stat;
import rznw.game.stat.StatFactory;
import rznw.map.GameWorld;

public class CharacterScreenRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 4;
    private static final int MENU_ROW_HEIGHT = 1;

    GameWorld gameWorld;

    public CharacterScreenRenderer(MainGameFrame frame, GameWorld gameWorld)
    {
        super(frame);

        this.gameWorld = gameWorld;
    }

    public void render(MenuState state, boolean showingDescription)
    {
        this.clearScreen();

        if (state.getEntryNumber() == 0)
        {
            this.renderCenteredString(1, "Character");

            this.renderCharacterInfo();
        } else {
            if (showingDescription)
            {
                MainCharacter mainCharacter = this.gameWorld.getMainCharacter();

                this.renderCenteredString(1, mainCharacter.getStatName(state.getEntryNumber() - 1));

                int row = 3;

                row += this.renderStringWithNewlines(3, mainCharacter.getStatDescription(state.getEntryNumber() - 1));
                row++;

                int statPoints = mainCharacter.getStatPoints(state.getEntryNumber() - 1);
                StatFactory factory = mainCharacter.getStatFactory();
                Stat stat = factory.getStat(state.getEntryNumber() - 1);

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

                this.renderCenteredString(29, "Press 'i' to return to the stat menu");
            }
            else
            {
                this.renderStatInfo(state);

                this.renderCursor(state);
            }
        }
    }

    private void renderCharacterInfo()
    {
        MainCharacter character = this.gameWorld.getMainCharacter();
        this.frame.renderDisplayString(3, 0, "Class: " + character.getCharacterClass());
        String levelDisplay = "" + character.getLevel();
        if (character.getLevel() == MainCharacter.MAX_LEVEL)
        {
            levelDisplay += " (max)";
        }
        this.frame.renderDisplayString(4, 0, "Level: " + levelDisplay);
        this.frame.renderDisplayString(5, 0, "Total experience: " + character.getExperience());

        int requiredTotalExperience = ExperienceCalculator.getRequiredExperience(character.getLevel() + 1);
        int requiredAdditionalExperience = requiredTotalExperience - character.getExperience();
        String requiredExperienceDisplay = requiredTotalExperience + " (" + requiredAdditionalExperience + " more)";
        if (character.getLevel() == MainCharacter.MAX_LEVEL)
        {
            requiredExperienceDisplay = "N/A";
        }
        this.frame.renderDisplayString(6, 0, "Next level: " + requiredExperienceDisplay);

        this.frame.renderDisplayString(8, 0, "Gold: " + character.getInventory().getNumGold());

        this.frame.renderDisplayString(10, 0, "HP: " + character.getHP() + "/" + character.getMaxHP());
        this.frame.renderDisplayString(11, 0, "MP: " + character.getMP() + "/" + character.getMaxMP());

        this.frame.renderDisplayString(31, 0, "Down for more");
    }

    private void renderStatInfo(MenuState state)
    {
        MainCharacter character = this.gameWorld.getMainCharacter();
        this.renderPointGroup(character, 0, 3);
        this.renderPointGroup(character, 1, 9);
        this.renderPointGroup(character, 2, 15);
        this.renderPointGroup(character, 3, 21);

        this.renderCenteredString(29, "Press 'i' for stat information");

        if (state.getEntryNumber() == 1)
        {
            this.frame.renderDisplayString(31, 0, "Up for more");
        }
    }

    private void renderPointGroup(MainCharacter mainCharacter, int groupNumber, int startRow)
    {
        String groupDisplay = MainCharacter.getStatCategory(groupNumber);
        this.frame.renderDisplayString(startRow, 2, groupDisplay);

        for (int i = 0; i < 4; i++)
        {
            int pointIndex = groupNumber * 4 + i;
            this.frame.renderDisplayString(startRow + i + 1, 2, MainCharacter.getStatName(pointIndex) + ": " + mainCharacter.getStatPoints(pointIndex));
        }
    }

    private void renderCursor(MenuState state)
    {
        int rowSpaces = 2 * ((state.getEntryNumber() - 1) / 4);
        int row = CharacterScreenRenderer.MENU_ENTRY_FIRST_ROW + (state.getEntryNumber() - 1) * CharacterScreenRenderer.MENU_ROW_HEIGHT + rowSpaces;

        this.frame.renderDisplayCharacter(row, 0, 'X');
    }
}

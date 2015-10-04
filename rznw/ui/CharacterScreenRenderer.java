package rznw.ui;

import rznw.game.maincharacter.ExperienceCalculator;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class CharacterScreenRenderer extends MenuScreenRenderer
{
    GameWorld gameWorld;

    public CharacterScreenRenderer(MainGameFrame frame, GameWorld gameWorld)
    {
        super(frame);

        this.gameWorld = gameWorld;
    }

    public void render(MenuState state)
    {
        this.clearScreen();

        this.renderCenteredString(1, "Character");

        if (state.getEntryNumber() == 0)
        {
            this.renderCharacterInfo();
        } else {
            this.renderStatInfo();
        }
    }

    private void renderCharacterInfo()
    {
        MainCharacter character = this.gameWorld.getMainCharacter();
        this.frame.renderDisplayString(3, 0, "Class: " + character.getCharacterClass());
        this.frame.renderDisplayString(4, 0, "Level: " + character.getLevel());
        this.frame.renderDisplayString(5, 0, "Total experience: " + character.getExperience());

        int requiredTotalExperience = ExperienceCalculator.getRequiredExperience(character.getLevel() + 1);
        int requiredAdditionalExperience = requiredTotalExperience - character.getExperience();
        this.frame.renderDisplayString(6, 0, "Next level: " + requiredTotalExperience + " (" + requiredAdditionalExperience + " more)");

        this.frame.renderDisplayString(8, 0, "Gold: " + character.getInventory().getNumGold());

        this.frame.renderDisplayString(10, 0, "HP: " + character.getHP() + "/" + character.getMaxHP());

        this.frame.renderDisplayString(31, 0, "Down for more");
    }

    private void renderStatInfo()
    {
        MainCharacter character = this.gameWorld.getMainCharacter();
        this.renderPointGroup(character, 0, 3);
        this.renderPointGroup(character, 1, 9);
        this.renderPointGroup(character, 2, 15);
        this.renderPointGroup(character, 3, 21);

        this.frame.renderDisplayString(31, 0, "Up for more");
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
}

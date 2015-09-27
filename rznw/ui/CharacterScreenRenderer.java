package rznw.ui;

import rznw.game.maincharacter.ExperienceCalculator;
import rznw.game.maincharacter.MainCharacter;

public class CharacterScreenRenderer extends MenuScreenRenderer
{
    MainCharacter character;

    public CharacterScreenRenderer(MainGameFrame frame, MainCharacter character)
    {
        super(frame);

        this.character = character;
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
        this.frame.renderDisplayString(3, 0, "Class: " + this.character.getCharacterClass());
        this.frame.renderDisplayString(4, 0, "Level: " + this.character.getLevel());
        this.frame.renderDisplayString(5, 0, "Total experience: " + this.character.getExperience());

        int requiredTotalExperience = ExperienceCalculator.getRequiredExperience(this.character.getLevel() + 1);
        int requiredAdditionalExperience = requiredTotalExperience - this.character.getExperience();
        this.frame.renderDisplayString(6, 0, "Next level: " + requiredTotalExperience + " (" + requiredAdditionalExperience + " more)");

        this.frame.renderDisplayString(8, 0, "Gold: " + this.character.getInventory().getNumGold());

        this.frame.renderDisplayString(10, 0, "HP: " + this.character.getHP() + "/" + this.character.getMaxHP());

        this.frame.renderDisplayString(31, 0, "Down for more");
    }

    private void renderStatInfo()
    {
        this.renderPointGroup(this.character, 0, 3);
        this.renderPointGroup(this.character, 1, 9);
        this.renderPointGroup(this.character, 2, 15);
        this.renderPointGroup(this.character, 3, 21);

        this.frame.renderDisplayString(31, 0, "Up for more");
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
}

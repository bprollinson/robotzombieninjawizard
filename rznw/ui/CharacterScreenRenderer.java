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

    public void render()
    {
        this.clearScreen();

        this.renderCenteredString(1, "Character");
        this.frame.renderDisplayString(3, 0, "Class: " + this.character.getCharacterClass());
        this.frame.renderDisplayString(4, 0, "Level: " + this.character.getLevel());
        this.frame.renderDisplayString(5, 0, "Total experience: " + this.character.getExperience());

        int requiredTotalExperience = ExperienceCalculator.getRequiredExperience(this.character.getLevel() + 1);
        int requiredAdditionalExperience = requiredTotalExperience - this.character.getExperience();
        this.frame.renderDisplayString(6, 0, "Next level: " + requiredTotalExperience + " (" + requiredAdditionalExperience + " more)");

        this.frame.renderDisplayString(8, 0, "HP: " + this.character.getHP() + "/" + this.character.getMaxHP());
    }
}

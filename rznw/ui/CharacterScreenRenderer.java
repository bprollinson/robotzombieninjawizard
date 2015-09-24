package rznw.ui;

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
        this.frame.renderDisplayString(5, 0, "HP: " + this.character.getHP() + "/" + this.character.getMaxHP());
    }
}

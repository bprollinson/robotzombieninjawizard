package rznw.map.element;

import rznw.game.maincharacter.MainCharacter;

public class MainCharacterMapElement extends CharacterMapElement
{
    private MainCharacter mainCharacter;

    public MainCharacterMapElement(int row, int column, char displayCharacter, MainCharacter mainCharacter)
    {
        super(row, column, displayCharacter);

        this.mainCharacter = mainCharacter;
    }

    public MainCharacter getMainCharacter()
    {
        return this.mainCharacter;
    }
}

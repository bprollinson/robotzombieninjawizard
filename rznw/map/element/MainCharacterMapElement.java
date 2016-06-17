package rznw.map.element;

import rznw.game.maincharacter.MainCharacter;

public class MainCharacterMapElement extends CharacterMapElement
{
    private static final int ELEMENT_NUMBER = 5;

    public MainCharacterMapElement(int row, int column, char displayCharacter, MainCharacter mainCharacter)
    {
        super(row, column, displayCharacter, mainCharacter);
    }

    public int getElementNumber()
    {
        return MainCharacterMapElement.ELEMENT_NUMBER;
    }
}

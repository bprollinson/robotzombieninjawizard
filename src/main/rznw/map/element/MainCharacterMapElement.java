package rznw.map.element;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;

public class MainCharacterMapElement extends CharacterMapElement
{
    public static final int ELEMENT_NUMBER = 5;

    public MainCharacterMapElement(int row, int column, char displayCharacter, MainCharacter mainCharacter)
    {
        super(row, column, displayCharacter, mainCharacter);
    }

    public int getElementNumber()
    {
        return MainCharacterMapElement.ELEMENT_NUMBER;
    }

    public boolean interactsWithCharacter(Character character)
    {
        return !character.isSummon();
    }
}

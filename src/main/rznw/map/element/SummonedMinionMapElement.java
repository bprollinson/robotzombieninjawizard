package rznw.map.element;

import rznw.game.Character;
import rznw.game.SummonedCharacter;
import rznw.map.Map;

public abstract class SummonedMinionMapElement extends CharacterMapElement
{
    public SummonedMinionMapElement(int row, int column, char displayCharacter, SummonedCharacter character)
    {
        super(row, column, displayCharacter, character);
    }

    public boolean interactsWithCharacter(Character character)
    {
        return true;
    }
}

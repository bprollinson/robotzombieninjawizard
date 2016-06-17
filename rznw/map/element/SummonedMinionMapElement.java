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

    public void processTurn(Map map)
    {
        Character enemy = this.getCharacter();
        if (enemy.isDead())
        {
            map.setElement(this.getRow(), this.getColumn(), null);
        }
    }
}

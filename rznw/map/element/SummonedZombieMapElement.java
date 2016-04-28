package rznw.map.element;

import rznw.game.SummonedCharacter;

public class SummonedZombieMapElement extends SummonedMinionMapElement
{
    public SummonedZombieMapElement(int row, int column, SummonedCharacter summonedCharacter)
    {
        super(row, column, 'S', summonedCharacter);
    }
}

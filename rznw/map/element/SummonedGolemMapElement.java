package rznw.map.element;

import rznw.game.SummonedCharacter;

public class SummonedGolemMapElement extends SummonedMinionMapElement
{
    public SummonedGolemMapElement(int row, int column, SummonedCharacter summonedCharacter)
    {
        super(row, column, 'G', summonedCharacter);
    }
}

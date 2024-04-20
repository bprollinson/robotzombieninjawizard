package rznw.map.element;

import rznw.game.SummonedCharacter;

public class SummonedGolemMapElement extends SummonedMinionMapElement
{
    public static final int ELEMENT_NUMBER = 10;

    public SummonedGolemMapElement(int row, int column, SummonedCharacter summonedCharacter)
    {
        super(row, column, 'G', summonedCharacter);
    }

    public int getElementNumber()
    {
        return SummonedGolemMapElement.ELEMENT_NUMBER;
    }
}

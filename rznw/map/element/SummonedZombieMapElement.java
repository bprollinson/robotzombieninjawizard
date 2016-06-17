package rznw.map.element;

import rznw.game.SummonedCharacter;

public class SummonedZombieMapElement extends SummonedMinionMapElement
{
    private static final int ELEMENT_NUMBER = 11;

    public SummonedZombieMapElement(int row, int column, SummonedCharacter summonedCharacter)
    {
        super(row, column, 'S', summonedCharacter);
    }

    public int getElementNumber()
    {
        return SummonedZombieMapElement.ELEMENT_NUMBER;
    }
}

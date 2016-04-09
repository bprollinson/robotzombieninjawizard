package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public class LeechMail extends Armor
{
    public String getDisplayName()
    {
        return "Leech Mail";
    }

    public int getDodgePercent()
    {
        return 4;
    }

    public int getPaddingPercent()
    {
        return 4;
    }

    public int getValue()
    {
        return 200;
    }

    public void step(MainCharacter character)
    {
        character.healMP(1);
    }
}

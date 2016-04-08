package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public class HealShield extends Shield
{
    public String getDisplayName()
    {
        return "Heal Shield";
    }

    public int getDodgePercent()
    {
        return 6;
    }

    public int getPaddingPercent()
    {
        return 3;
    }

    public int getValue()
    {
        return 200;
    }

    public void dodgesAttack(MainCharacter mainCharacter)
    {
        System.out.println("Dodged attack with heal shield - healing 5 HP");

        mainCharacter.heal(5);
    }
}

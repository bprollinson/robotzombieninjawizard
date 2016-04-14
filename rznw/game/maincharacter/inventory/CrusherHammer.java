package rznw.game.maincharacter.inventory;

public class CrusherHammer extends Weapon
{
    public String getDisplayName()
    {
        return "Crusher Hammer";
    }

    public int getDamage()
    {
        return 30;
    }

    public int getToHitBonus()
    {
        return -10;
    }

    public int getValue()
    {
        return 200;
    }
}

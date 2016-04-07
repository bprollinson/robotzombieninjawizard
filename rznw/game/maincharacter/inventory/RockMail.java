package rznw.game.maincharacter.inventory;

public class RockMail extends Armor
{
    public String getDisplayName()
    {
        return "Rock Mail";
    }

    public int getDodgePercent()
    {
        return 1;
    }

    public int getPaddingPercent()
    {
        return 10;
    }

    public int getValue()
    {
        return 200;
    }
}

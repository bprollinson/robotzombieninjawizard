package rznw.game.maincharacter.inventory;

public class MagicShield extends Shield
{
    public String getDisplayName()
    {
        return "Magic Shield";
    }

    public int getDodgePercent()
    {
        return 4;
    }

    public int getPaddingPercent()
    {
        return 4;
    }

    public int getMagicDodgePercent()
    {
        return 20;
    }

    public int getMagicPaddingPercent()
    {
        return 20;
    }

    public int getValue()
    {
        return 400;
    }
}

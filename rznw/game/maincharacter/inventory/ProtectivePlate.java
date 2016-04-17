package rznw.game.maincharacter.inventory;

public class ProtectivePlate extends Armor
{
    public String getDisplayName()
    {
        return "Protective Plate";
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

    public int getThickSkinBonus()
    {
        return 50;
    }
}

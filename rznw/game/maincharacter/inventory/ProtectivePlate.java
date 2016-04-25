package rznw.game.maincharacter.inventory;

public class ProtectivePlate extends Armor
{
    public String getDisplayName()
    {
        return "Protective Plate";
    }

    public String[] getStats()
    {
        return new String[] {
            "Dodge: " + this.getDodgePercent() + "%",
            "Padding: " + this.getPaddingPercent() + "%",
            "",
            "Value: " + this.getValue()
        };
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

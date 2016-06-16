package rznw.game.maincharacter.inventory;

public class WoodenShield extends Shield
{
    public static final int EQUIPMENT_NUMBER = 20;

    public String getDisplayName()
    {
        return "Wooden Shield";
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
        return 5;
    }

    public int getPaddingPercent()
    {
        return 5;
    }

    public int getValue()
    {
        return 200;
    }

    public int getEquipmentNumber()
    {
        return WoodenShield.EQUIPMENT_NUMBER;
    }
}

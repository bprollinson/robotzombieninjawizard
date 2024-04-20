package rznw.game.maincharacter.inventory;

public class ShieldOfSight extends Shield
{
    public static final int EQUIPMENT_NUMBER = 19;

    public String getDisplayName()
    {
        return "Shield of Sight";
    }

    public String[] getStats()
    {
        return new String[] {
            "Dodge: " + this.getDodgePercent() + "%",
            "Padding: " + this.getPaddingPercent() + "%",
            "View radius bonus: " + this.getViewRadiusBonus(),
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

    public int getViewRadiusBonus()
    {
        return 4;
    }

    public int getEquipmentNumber()
    {
        return ShieldOfSight.EQUIPMENT_NUMBER;
    }
}

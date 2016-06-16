package rznw.game.maincharacter.inventory;

public class RockMail extends Armor
{
    private static final int EQUIPMENT_NUMBER = 26;

    public String getDisplayName()
    {
        return "Rock Mail";
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
        return 1;
    }

    public int getPaddingPercent()
    {
        return 10;
    }

    public int getEquipmentNumber()
    {
        return RockMail.EQUIPMENT_NUMBER;
    }
}

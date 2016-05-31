package rznw.game.maincharacter.inventory;

public class RockMail extends Armor
{
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
}

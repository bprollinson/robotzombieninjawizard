package rznw.game.maincharacter.inventory;

public class AssassinsCloak extends Armor
{
    public String getDisplayName()
    {
        return "Assassin's Cloak";
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
        return 10;
    }

    public int getPaddingPercent()
    {
        return 1;
    }
}

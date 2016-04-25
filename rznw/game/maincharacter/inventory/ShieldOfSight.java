package rznw.game.maincharacter.inventory;

public class ShieldOfSight extends Shield
{
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

    public int getValue()
    {
        return 400;
    }

    public int getViewRadiusBonus()
    {
        return 4;
    }
}

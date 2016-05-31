package rznw.game.maincharacter.inventory;

public class MagicShield extends Shield
{
    public String getDisplayName()
    {
        return "Magic Shield";
    }

    public String[] getStats()
    {
        return new String[] {
            "Dodge: " + this.getDodgePercent() + "%",
            "Padding: " + this.getPaddingPercent() + "%",
            "Dodge against magic attacks: + " + this.getMagicDodgePercent() + "%",
            "Padding against magic attacks: + " + this.getMagicPaddingPercent() + "%",
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

    public int getMagicDodgePercent()
    {
        return 20;
    }

    public int getMagicPaddingPercent()
    {
        return 20;
    }
}

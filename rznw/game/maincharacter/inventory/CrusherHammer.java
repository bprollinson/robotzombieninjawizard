package rznw.game.maincharacter.inventory;

public class CrusherHammer extends Weapon
{
    public String getDisplayName()
    {
        return "Crusher Hammer";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "To hit bonus: " + this.getToHitBonus() + "%",
            "",
            "Value: " + this.getValue()
        };
    }

    public int getDamage()
    {
        return 30;
    }

    public int getToHitBonus()
    {
        return -10;
    }
}

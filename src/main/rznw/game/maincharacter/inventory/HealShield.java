package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.ui.LogRendererFactory;

public class HealShield extends Shield
{
    public static final int EQUIPMENT_NUMBER = 17;

    public String getDisplayName()
    {
        return "Heal Shield";
    }

    public String[] getStats()
    {
        return new String[] {
            "Dodge: " + this.getDodgePercent() + "%",
            "Padding: " + this.getPaddingPercent() + "%",
            "Heals you when you dodge an enemy attack",
            "Heal amount: 5 HP",
            "",
            "Value: " + this.getValue()
        };
    }

    public int getDodgePercent()
    {
        return 6;
    }

    public int getPaddingPercent()
    {
        return 3;
    }

    public void dodgesAttack(MainCharacter mainCharacter)
    {
        mainCharacter.heal(5);

        LogRendererFactory.instance().log("You healed 5 HP.");
    }

    public int getEquipmentNumber()
    {
        return HealShield.EQUIPMENT_NUMBER;
    }
}

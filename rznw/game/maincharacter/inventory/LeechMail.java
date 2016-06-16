package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public class LeechMail extends Armor
{
    private static final int EQUIPMENT_NUMBER = 23;

    public String getDisplayName()
    {
        return "Leech Mail";
    }

    public String[] getStats()
    {
        return new String[] {
            "Dodge: " + this.getDodgePercent() + "%",
            "Padding: " + this.getPaddingPercent() + "%",
            "Recovers your MP as you explore new territory",
            "MP recovered: 1 per step",
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

    public void step(MainCharacter character)
    {
        character.healMP(1);
    }

    public int getEquipmentNumber()
    {
        return LeechMail.EQUIPMENT_NUMBER;
    }
}

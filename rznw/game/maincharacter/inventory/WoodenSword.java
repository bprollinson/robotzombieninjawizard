package rznw.game.maincharacter.inventory;

public class WoodenSword extends Weapon
{
    public static final int EQUIPMENT_NUMBER = 14;

    public String getDisplayName()
    {
        return "Wooden Sword";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "",
            "Value: " + this.getValue()
        };
    }

    public int getDamage()
    {
        return 10;
    }

    public int getValue()
    {
        return 200;
    }

    public int getEquipmentNumber()
    {
        return WoodenSword.EQUIPMENT_NUMBER;
    }
}

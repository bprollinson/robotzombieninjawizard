package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public abstract class Shield extends EquipmentItem
{
    public static final int EQUIPMENT_TYPE = 2;

    public abstract int getDodgePercent();

    public abstract int getPaddingPercent();

    public int getEquipmentType()
    {
        return Shield.EQUIPMENT_TYPE;
    }

    public int getMagicDodgePercent()
    {
        return 0;
    }

    public int getMagicPaddingPercent()
    {
        return 0;
    }

    public void dodgesAttack(MainCharacter mainCharacter)
    {
    }

    public int getViewRadiusBonus()
    {
        return 0;
    }

    public boolean isShield()
    {
        return true;
    }
}

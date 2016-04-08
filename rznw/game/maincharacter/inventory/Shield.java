package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public abstract class Shield extends EquipmentItem
{
    public abstract int getDodgePercent();

    public abstract int getPaddingPercent();

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
}

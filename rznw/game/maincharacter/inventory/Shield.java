package rznw.game.maincharacter.inventory;

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
}

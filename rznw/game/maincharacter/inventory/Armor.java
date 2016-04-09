package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public abstract class Armor extends EquipmentItem
{
    public abstract int getDodgePercent();

    public abstract int getPaddingPercent();

    public void step(MainCharacter character)
    {
    }
}

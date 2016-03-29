package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public abstract class InventoryItem
{
    public abstract String getDisplayName();

    public abstract String getDescription();

    public abstract void useOnCharacter(MainCharacter character);

    public abstract int getValue();

    public int getBuyPrice(MainCharacter character)
    {
        int priceReductionPercent = (int)(2.0 * character.getSkillPoints(4));

        return (int)Math.ceil((100.0 - priceReductionPercent) / 100.0 * this.getValue());
    }

    public int getSellPrice()
    {
        return (int)Math.floor(0.6 * this.getValue());
    }
}

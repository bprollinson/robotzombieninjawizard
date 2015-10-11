package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public abstract class InventoryItem
{
    public abstract String getDisplayName();

    public abstract void useOnCharacter(MainCharacter character);
}

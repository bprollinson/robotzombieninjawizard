package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class ManaPotion extends InventoryItem
{
    public static final int ITEM_NUMBER = 5;

    public String getDisplayName()
    {
        return "Mana Potion";
    }

    public String getDescription()
    {
        return "Heals 50 MP.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        character.healMP(50);
    }

    public int getValue()
    {
        return 100;
    }

    public int getItemNumber()
    {
        return ManaPotion.ITEM_NUMBER;
    }
}

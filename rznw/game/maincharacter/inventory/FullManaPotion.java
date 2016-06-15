package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class FullManaPotion extends InventoryItem
{
    public static final int ITEM_NUMBER = 2;

    public String getDisplayName()
    {
        return "Full Mana Potion";
    }

    public String getDescription()
    {
        return "Heals to full MP.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        character.setMP(character.getMaxMP());
    }

    public int getValue()
    {
        return 200;
    }

    public int getItemNumber()
    {
        return FullManaPotion.ITEM_NUMBER;
    }
}

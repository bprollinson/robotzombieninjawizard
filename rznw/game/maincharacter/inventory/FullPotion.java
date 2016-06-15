package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class FullPotion extends InventoryItem
{
    public static final int ITEM_NUMBER = 3;

    public String getDisplayName()
    {
        return "Full Potion";
    }

    public String getDescription()
    {
        return "Heals to full HP.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        character.setHP(character.getMaxHP());
    }

    public int getValue()
    {
        return 200;
    }

    public int getItemNumber()
    {
        return FullPotion.ITEM_NUMBER;
    }
}

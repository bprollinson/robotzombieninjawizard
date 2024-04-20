package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

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
        LogRendererFactory.instance().log("Using mana potion.");

        character.healMP(50);
        LogRendererFactory.instance().log("You recovered 50 MP.");
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

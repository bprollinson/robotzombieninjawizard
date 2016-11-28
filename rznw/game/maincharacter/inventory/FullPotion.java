package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

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
        LogRendererFactory.instance().log("Using full potion.");

        character.setHP(character.getMaxHP());
        LogRendererFactory.instance().log("You healed all HP.");
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

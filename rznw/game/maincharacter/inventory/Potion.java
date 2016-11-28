package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class Potion extends InventoryItem
{
    public static final int ITEM_NUMBER = 7;

    public String getDisplayName()
    {
        return "Potion";
    }

    public String getDescription()
    {
        return "Heals 50 HP.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        LogRendererFactory.instance().log("Using potion.");

        int HPHealed = character.heal(50);
        LogRendererFactory.instance().log("You healed " + HPHealed + " HP.");
    }

    public int getValue()
    {
        return 100;
    }

    public int getItemNumber()
    {
        return Potion.ITEM_NUMBER;
    }
}

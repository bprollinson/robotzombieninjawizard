package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class ReplenishingFullPotion extends InventoryItem
{
    public static final int ITEM_NUMBER = 9;

    public String getDisplayName()
    {
        return "Replenishing Full Potion";
    }

    public String getDescription()
    {
        return "Heals to full HP and has a chance to replenish.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        LogRendererFactory.instance().log("Using replenishing full potion.");

        character.setHP(character.getMaxHP());
        LogRendererFactory.instance().log("You healed all HP.");

        if (RandomNumberGenerator.rollSucceeds(25))
        {
            try
            {
                character.getInventory().addItems(new InventoryItemGroup(new ReplenishingFullPotion(), 1));
                LogRendererFactory.instance().log("Replenished potion.");
            }
            catch (InventoryFullException ife)
            {
            }
        }
    }

    public int getValue()
    {
        return 400;
    }

    public int getItemNumber()
    {
        return ReplenishingFullPotion.ITEM_NUMBER;
    }
}

package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class ReplenishingFullManaPotion extends InventoryItem
{
    public static final int ITEM_NUMBER = 8;

    public String getDisplayName()
    {
        return "Replenishing Full Mana Potion";
    }

    public String getDescription()
    {
        return "Heals to full MP and has a chance to replenish.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        LogRendererFactory.instance().log("Using replenishing full mana potion.");

        character.setMP(character.getMaxMP());
        LogRendererFactory.instance().log("You recovered all MP.");

        if (RandomNumberGenerator.rollSucceeds(25))
        {
            try
            {
                character.getInventory().addItems(new InventoryItemGroup(new ReplenishingFullManaPotion(), 1));
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
        return ReplenishingFullManaPotion.ITEM_NUMBER;
    }
}

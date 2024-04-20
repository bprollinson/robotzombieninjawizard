package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class ReplenishingHerb extends InventoryItem
{
    public static final int ITEM_NUMBER = 10;

    public String getDisplayName()
    {
        return "Replenishing Herb";
    }

    public String getDescription()
    {
        return "Cures poison and has a chance to replenish.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        LogRendererFactory.instance().log("Using replenishing herb.");

        character.getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_POISONED, false);
        LogRendererFactory.instance().log("You cured all poison.");

        if (RandomNumberGenerator.rollSucceeds(25))
        {
            try
            {
                character.getInventory().addItems(new InventoryItemGroup(new ReplenishingHerb(), 1));
                LogRendererFactory.instance().log("Replenished herb.");
            }
            catch (InventoryFullException ife)
            {
            }
        }
    }

    public int getValue()
    {
        return 100;
    }

    public int getItemNumber()
    {
        return ReplenishingHerb.ITEM_NUMBER;
    }
}

package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class ReplenishingSanityDrop extends InventoryItem
{
    public static final int ITEM_NUMBER = 11;

    public String getDisplayName()
    {
        return "Replenishing Sanity Drop";
    }

    public String getDescription()
    {
        return "Cures confusion and has a chance to replenish.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        LogRendererFactory.instance().log("Using replenishing sanity drop.");

        character.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_CONFUSION, 0);
        LogRendererFactory.instance().log("You cured all confusion.");

        if (RandomNumberGenerator.rollSucceeds(25))
        {
            try
            {
                character.getInventory().addItems(new InventoryItemGroup(new ReplenishingSanityDrop(), 1));
                LogRendererFactory.instance().log("Replenished drop.");
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
        return ReplenishingSanityDrop.ITEM_NUMBER;
    }
}

package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class SanityDrop extends InventoryItem
{
    public static final int ITEM_NUMBER = 13;

    public String getDisplayName()
    {
        return "Sanity Drop";
    }

    public String getDescription()
    {
        return "Cures confusion.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        LogRendererFactory.instance().log("Using sanity drop.");

        character.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_CONFUSION, 0);
        LogRendererFactory.instance().log("You cured all confusion.");
    }

    public int getValue()
    {
        return 50;
    }

    public int getItemNumber()
    {
        return SanityDrop.ITEM_NUMBER;
    }
}

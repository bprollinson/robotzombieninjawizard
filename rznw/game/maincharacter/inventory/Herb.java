package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class Herb extends InventoryItem
{
    public static final int ITEM_NUMBER = 4;

    public String getDisplayName()
    {
        return "Herb";
    }

    public String getDescription()
    {
        return "Cures poison.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        LogRendererFactory.instance().log("Using herb.");

        character.getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_POISONED, false);
        LogRendererFactory.instance().log("You cured all poison.");
    }

    public int getValue()
    {
        return 50;
    }

    public int getItemNumber()
    {
        return Herb.ITEM_NUMBER;
    }
}

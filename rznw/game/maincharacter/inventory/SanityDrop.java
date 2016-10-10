package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;

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
        character.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_CONFUSION, 0);
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

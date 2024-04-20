package rznw.game.spell.robot;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.InventoryItemUpgrader;
import rznw.game.spell.UndirectedSpell;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class PowerSearchSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Power Search";
    }

    public String getDescription()
    {
        return "Temporarily increases the power level of items dropped by enemies.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting power search.");

        MainCharacter character = gameWorld.getMainCharacter();
        int numTurns = 1 + spellPoints;
        character.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_POWER_SEARCH, numTurns);
        LogRendererFactory.instance().log("Power search enabled.");
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Turns: " + (1 + spellPoints),
            "Chance to upgrade: " + 5 * spellPoints + "%"
        };
    }

    public static InventoryItemGroup getUpgradedItemGroup(InventoryItemGroup itemGroup, int spellPoints)
    {
        if (!RandomNumberGenerator.rollSucceeds(5 * spellPoints))
        {
            return itemGroup;
        }

        return new InventoryItemUpgrader().getUpgradedItemGroup(itemGroup);
    }
}

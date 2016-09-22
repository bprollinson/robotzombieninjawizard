package rznw.game.spell.robot;

import rznw.game.StatusEffects;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Bomb;
import rznw.game.maincharacter.inventory.FullManaPotion;
import rznw.game.maincharacter.inventory.FullPotion;
import rznw.game.maincharacter.inventory.Herb;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.ManaPotion;
import rznw.game.maincharacter.inventory.MegaBomb;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.maincharacter.inventory.ReplenishingFullManaPotion;
import rznw.game.maincharacter.inventory.ReplenishingFullPotion;
import rznw.game.maincharacter.inventory.ReplenishingHerb;
import rznw.game.maincharacter.inventory.ReplenishingSanityDrop;
import rznw.game.maincharacter.inventory.ReplenishingXRayDrop;
import rznw.game.maincharacter.inventory.SanityDrop;
import rznw.game.maincharacter.inventory.XRayDrop;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
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
        System.out.println("Casting Power Search");

        MainCharacter character = gameWorld.getMainCharacter();
        int numTurns = 1 + spellPoints;
        character.getStatusEffects().setStatusEffectTurns(StatusEffects.EFFECT_POWER_SEARCH, numTurns);
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
            System.out.println("Power search spell fail!");
            return itemGroup;
        }

        InventoryItem item = itemGroup.getItem();

        if (item instanceof Potion)
        {
            return new InventoryItemGroup(new FullPotion(), 1);
        }

        if (item instanceof ManaPotion)
        {
            return new InventoryItemGroup(new FullManaPotion(), 1);
        }

        if (item instanceof FullPotion)
        {
            return new InventoryItemGroup(new ReplenishingFullPotion(), 1);
        }

        if (item instanceof FullManaPotion)
        {
            return new InventoryItemGroup(new ReplenishingFullManaPotion(), 1);
        }

        if (item instanceof Herb)
        {
            return new InventoryItemGroup(new ReplenishingHerb(), 1);
        }

        if (item instanceof SanityDrop)
        {
            return new InventoryItemGroup(new ReplenishingSanityDrop(), 1);
        }

        if (item instanceof XRayDrop)
        {
            return new InventoryItemGroup(new ReplenishingXRayDrop(), 1);
        }

        if (item instanceof Bomb)
        {
            return new InventoryItemGroup(new MegaBomb(), 1);
        }

        return itemGroup;
    }
}

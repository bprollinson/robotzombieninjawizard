package rznw.game.maincharacter.inventory;

public class InventoryItemUpgrader
{
    public InventoryItemGroup getUpgradedItemGroup(InventoryItemGroup itemGroup)
    {
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

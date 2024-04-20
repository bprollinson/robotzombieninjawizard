package rznw.game.maincharacter.inventory;

import rznw.utility.RandomNumberGenerator;

public class RandomTradeItemGenerator
{
    public static InventoryItem generateRandomTradeItem()
    {
        InventoryItem[] possibleItems = new InventoryItem[] {
            new Bomb(),
            new FullManaPotion(),
            new FullPotion(),
            new Herb(),
            new ManaPotion(),
            new MegaBomb(),
            new Potion(),
            new ReplenishingFullManaPotion(),
            new ReplenishingFullPotion(),
            new ReplenishingHerb(),
            new ReplenishingSanityDrop(),
            new ReplenishingXRayDrop(),
            new SanityDrop(),
            new XRayDrop()
        };

        int index = RandomNumberGenerator.randomInteger(0, possibleItems.length - 1);

        return possibleItems[index];
    }
}

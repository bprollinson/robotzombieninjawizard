package rznw.save;

import rznw.game.maincharacter.inventory.Bomb;
import rznw.game.maincharacter.inventory.FullManaPotion;
import rznw.game.maincharacter.inventory.FullPotion;
import rznw.game.maincharacter.inventory.Herb;
import rznw.game.maincharacter.inventory.InventoryItem;
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

public class InventoryItemFactory
{
    public static InventoryItem factory(int itemIndex)
    {
        switch (itemIndex)
        {
            case Bomb.ITEM_NUMBER:
                return new Bomb();
            case FullManaPotion.ITEM_NUMBER:
                return new FullManaPotion();
            case FullPotion.ITEM_NUMBER:
                return new FullPotion();
            case Herb.ITEM_NUMBER:
                return new Herb();
            case ManaPotion.ITEM_NUMBER:
                return new ManaPotion();
            case MegaBomb.ITEM_NUMBER:
                return new MegaBomb();
            case Potion.ITEM_NUMBER:
                return new Potion();
            case ReplenishingFullManaPotion.ITEM_NUMBER:
                return new ReplenishingFullManaPotion();
            case ReplenishingFullPotion.ITEM_NUMBER:
                return new ReplenishingFullPotion();
            case ReplenishingHerb.ITEM_NUMBER:
                return new ReplenishingHerb();
            case ReplenishingSanityDrop.ITEM_NUMBER:
                return new ReplenishingSanityDrop();
            case ReplenishingXRayDrop.ITEM_NUMBER:
                return new ReplenishingXRayDrop();
            case SanityDrop.ITEM_NUMBER:
                return new SanityDrop();
            case XRayDrop.ITEM_NUMBER:
                return new XRayDrop();
        }

        return null;
    }
}

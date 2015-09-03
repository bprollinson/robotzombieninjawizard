package rznw.game.maincharacter.inventory;

import java.util.Collection;
import java.util.Vector;

public class Inventory
{
    Collection<InventoryItem> itemSet;

    public Inventory()
    {
        this.itemSet = new Vector<InventoryItem>();
    }

    public void addItem(InventoryItem item)
    {
        this.itemSet.add(item);
        System.out.println("Size: " + this.itemSet.size());
    }
}

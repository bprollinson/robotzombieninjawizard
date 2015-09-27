package rznw.game.maincharacter.inventory;

import java.util.Collection;
import java.util.Vector;

public class Inventory
{
    int numGold = 0;
    Collection<InventoryItem> itemSet;

    public Inventory()
    {
        this.itemSet = new Vector<InventoryItem>();
    }

    public void addGold(int numGold)
    {
        this.numGold += numGold;
    }

    public int getNumGold()
    {
        return this.numGold;
    }

    public void addItem(InventoryItem item)
    {
        this.itemSet.add(item);
        System.out.println("Size: " + this.itemSet.size());
    }
}

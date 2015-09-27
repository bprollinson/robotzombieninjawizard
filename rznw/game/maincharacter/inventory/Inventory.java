package rznw.game.maincharacter.inventory;

import java.util.Vector;

public class Inventory
{
    int numGold = 0;
    Vector<InventoryItem> itemSet;

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

    public int getNumItems()
    {
        return itemSet.size();
    }

    public InventoryItem getItem(int index)
    {
        return this.itemSet.get(index);
    }
}

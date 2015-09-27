package rznw.game.maincharacter.inventory;

import java.util.Vector;

public class Inventory
{
    int numGold = 0;
    Vector<InventoryItemGroup> itemGroups;

    public Inventory()
    {
        this.itemGroups = new Vector<InventoryItemGroup>();
    }

    public void addGold(int numGold)
    {
        this.numGold += numGold;
    }

    public int getNumGold()
    {
        return this.numGold;
    }

    public void addItems(InventoryItemGroup itemGroup)
    {
        this.itemGroups.add(itemGroup);
    }

    public int getNumItemGroups()
    {
        return itemGroups.size();
    }

    public InventoryItemGroup getItemGroup(int index)
    {
        return this.itemGroups.get(index);
    }
}

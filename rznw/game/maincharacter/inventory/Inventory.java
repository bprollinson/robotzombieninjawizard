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
        int index = -1;

        for (int i = 0; i < this.itemGroups.size(); i++)
        {
            InventoryItemGroup existingItemGroup = this.itemGroups.get(i);
            if (itemGroup.getItem().getClass().equals(existingItemGroup.getItem().getClass()))
            {
                index = 0;
                break;
            }
        }

        if (index == -1)
        {
            this.itemGroups.add(itemGroup);
        }
        else
        {
            this.itemGroups.get(index).addItemsToGroup(itemGroup.getNumItems());
        }
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

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

    public void removeGold(int numGold)
    {
        this.numGold -= numGold;
    }

    public int getNumGold()
    {
        return this.numGold;
    }

    public void addItems(InventoryItemGroup itemGroup)
    {
        int index = this.getItemGroupPosition(itemGroup);

        if (index == -1)
        {
            this.itemGroups.add(itemGroup);
        }
        else
        {
            this.itemGroups.get(index).addItemsToGroup(itemGroup.getNumItems());
        }
    }

    public void removeItems(InventoryItemGroup itemGroup)
    {
        int index = this.getItemGroupPosition(itemGroup);

        if (index == -1)
        {
            return;
        }

        this.itemGroups.get(index).removeItemsFromGroup(itemGroup.getNumItems());

        if (this.itemGroups.get(index).getNumItems() == 0)
        {
            this.itemGroups.remove(index);
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

    private int getItemGroupPosition(InventoryItemGroup itemGroup)
    {
        int index = -1;

        for (int i = 0; i < this.itemGroups.size(); i++)
        {
            InventoryItemGroup existingItemGroup = this.itemGroups.get(i);
            if (itemGroup.getItem().getClass().equals(existingItemGroup.getItem().getClass()))
            {
                index = i;
                break;
            }
        }

        return index;
    }
}

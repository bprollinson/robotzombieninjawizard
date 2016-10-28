package rznw.game.maincharacter.inventory;

import java.util.Vector;

import rznw.game.maincharacter.MainCharacter;

public class Inventory
{
    int numGold = 0;
    Vector<InventoryItemGroup> itemGroups;
    MainCharacter character;

    public Inventory(MainCharacter character)
    {
        this.itemGroups = new Vector<InventoryItemGroup>();
        this.character = character;
    }

    public void addGold(int numGold)
    {
        this.numGold += numGold;
    }

    public void removeGold(int numGold)
    {
        this.numGold -= numGold;

        if (this.numGold < 0)
        {
            this.numGold = 0;
        }
    }

    public int getNumGold()
    {
        return this.numGold;
    }

    public void setNumGold(int numGold)
    {
        this.numGold = numGold;
    }

    public void addItems(InventoryItemGroup itemGroup) throws InventoryFullException
    {
        new AddInventoryAssertion(this.character).validate(itemGroup);

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

    public void addItems(Vector<InventoryItemGroup> itemGroups) throws InventoryFullException
    {
        for (int i = 0; i < itemGroups.size(); i++)
        {
            this.addItems(itemGroups.get(i));
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

    public int getItemGroupPosition(InventoryItemGroup itemGroup)
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

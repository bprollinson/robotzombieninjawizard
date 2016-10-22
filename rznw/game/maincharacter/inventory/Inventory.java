package rznw.game.maincharacter.inventory;

import java.util.Vector;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.stat.Stat;

public class Inventory
{
    int numGold = 999999;
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
        this.assertCanAddItems(itemGroup);

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

    private void assertCanAddItems(InventoryItemGroup itemGroup) throws InventoryFullException
    {
        if (this.character == null)
        {
            return;
        }

        int statPoints = this.character.getStats().getStatPoints(Stat.STAT_UNENCUMBERANCE);
        int maxSize = 1 + statPoints;

        int index = this.getItemGroupPosition(itemGroup);

        if (index == -1 && this.itemGroups.size() >= maxSize)
        {
            throw new InventoryFullException();
        }

        if (index != -1 && this.itemGroups.get(index).getNumItems() >= maxSize)
        {
            throw new InventoryFullException();
        }
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

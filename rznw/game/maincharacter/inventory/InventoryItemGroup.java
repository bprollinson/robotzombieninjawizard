package rznw.game.maincharacter.inventory;

public class InventoryItemGroup
{
    private InventoryItem item;
    private int numItems;

    public InventoryItemGroup(InventoryItem item, int numItems)
    {
        this.item = item;
        this.numItems = numItems;
    }

    public InventoryItem getItem()
    {
        return this.item;
    }

    public int getNumItems()
    {
        return this.numItems;
    }

    public void addItemsToGroup(int numItems)
    {
        this.numItems += numItems;
    }

    public String getDisplayString()
    {
        return this.numItems + " " + item.getDisplayName();
    }
}

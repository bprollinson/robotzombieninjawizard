package rznw.game.maincharacter.inventory;

public class EquipmentGroup
{
    private EquipmentItem item;
    private int numItems;

    public EquipmentGroup(EquipmentItem item, int numItems)
    {
        this.item = item;
        this.numItems = numItems;
    }

    public EquipmentItem getItem()
    {
        return this.item;
    }

    public int getNumItems()
    {
        return this.numItems;
    }

    public void addEquipmentToGroup(int numItems)
    {
        this.numItems += numItems;
    }
}

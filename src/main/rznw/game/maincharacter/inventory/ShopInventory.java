package rznw.game.maincharacter.inventory;

public class ShopInventory
{
    private Inventory selectedItems;
    private Equipment selectedEquipments;

    public ShopInventory()
    {
        this.selectedItems = new Inventory(null);
        this.selectedEquipments = new Equipment(null);
    }

    public Inventory getRandomItems()
    {
        return this.selectedItems;
    }

    public void setRandomItems(Inventory items)
    {
        this.selectedItems = items;
    }

    public Equipment getRandomEquipments()
    {
        return this.selectedEquipments;
    }

    public void setRandomEquipment(Equipment equipment)
    {
        this.selectedEquipments = equipment;
    }
}

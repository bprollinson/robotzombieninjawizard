package rznw.game.maincharacter.inventory;

import java.util.Vector;

public class ShopInventory
{
    private Vector<InventoryItemGroup> selectedItems;
    private Vector<EquipmentGroup> selectedEquipments;

    public ShopInventory()
    {
        this.selectedItems = new Vector<InventoryItemGroup>();
        this.selectedEquipments = new Vector<EquipmentGroup>();
    }

    public Vector<InventoryItemGroup> getRandomItems()
    {
        return this.selectedItems;
    }

    public void setRandomItems(Vector<InventoryItemGroup> items)
    {
        this.selectedItems = items;
    }

    public Vector<EquipmentGroup> getRandomEquipments()
    {
        return this.selectedEquipments;
    }

    public void setRandomEquipment(Vector<EquipmentGroup> equipment)
    {
        this.selectedEquipments = equipment;
    }
}

package rznw.game.maincharacter.inventory;

import java.util.Vector;

public class ShopInventory
{
    private Inventory selectedItems;
    private Vector<EquipmentGroup> selectedEquipments;

    public ShopInventory()
    {
        this.selectedItems = new Inventory(null);
        this.selectedEquipments = new Vector<EquipmentGroup>();
    }

    public Inventory getRandomItems()
    {
        return this.selectedItems;
    }

    public void setRandomItems(Inventory items)
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

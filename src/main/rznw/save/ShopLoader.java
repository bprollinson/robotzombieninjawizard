package rznw.save;

import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.EquipmentItemFactory;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryFullException;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.InventoryItemFactory;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.RandomInventoryGenerator;
import rznw.map.GameWorld;

import java.io.BufferedReader;

public class ShopLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        int status = this.readInteger(fileReader);

        if (status == 0)
        {
            return;
        }

        Inventory items = new Inventory(null);

        int numItemGroups = this.readInteger(fileReader);
        for (int i = 0; i < numItemGroups; i++)
        {
            int itemNumber = this.readInteger(fileReader);
            int numItems = this.readInteger(fileReader);

            InventoryItem item = InventoryItemFactory.factory(itemNumber);
            try
            {
                items.addItems(new InventoryItemGroup(item, numItems));
            }
            catch (InventoryFullException ife)
            {
            }
        }

        gameWorld.getShopInventory().setRandomItems(items);

        Equipment equipments = new Equipment(null);

        int numEquipmentGroups = this.readInteger(fileReader);
        for (int i = 0; i < numEquipmentGroups; i++)
        {
            int equipmentNumber = this.readInteger(fileReader);
            int numEquipment = this.readInteger(fileReader);

            EquipmentItem equipment = EquipmentItemFactory.factory(equipmentNumber);
            try
            {
                equipments.addEquipment(new EquipmentGroup(equipment, numEquipment));
            }
            catch (EquipmentFullException ife)
            {
            }
        }

        gameWorld.getShopInventory().setRandomEquipment(equipments);
    }
}

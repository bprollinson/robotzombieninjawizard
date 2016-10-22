package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.RandomInventoryGenerator;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.map.GameWorld;

import java.io.BufferedWriter;

public class ShopSaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        if (character.getStatusEffects().getStatusEffect(SimpleStatusEffects.EFFECT_REGENERATE_SHOP))
        {
            this.writeLine(fileWriter, 0);

            return;
        }

        this.writeLine(fileWriter, 1);

        Inventory items = gameWorld.getShopInventory().getRandomItems();
        this.writeLine(fileWriter, items.getNumItemGroups());

        for (int i = 0; i < items.getNumItemGroups(); i++)
        {
            InventoryItemGroup itemGroup = items.getItemGroup(i);

            this.writeLine(fileWriter, itemGroup.getItem().getItemNumber());
            this.writeLine(fileWriter, itemGroup.getNumItems());
        }

        Equipment equipment = gameWorld.getShopInventory().getRandomEquipments();
        this.writeLine(fileWriter, equipment.getNumGroups());

        for (int i = 0; i < equipment.getNumGroups(); i++)
        {
            EquipmentGroup equipmentGroup = equipment.getEquipmentGroup(i);

            this.writeLine(fileWriter, equipmentGroup.getItem().getEquipmentNumber());
            this.writeLine(fileWriter, equipmentGroup.getNumItems());
        }
    }
}

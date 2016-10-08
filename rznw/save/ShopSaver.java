package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.RandomInventoryGenerator;
import rznw.game.statuseffects.StatusEffects;
import rznw.map.GameWorld;

import java.io.BufferedWriter;
import java.util.Vector;

public class ShopSaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        if (character.getStatusEffects().getStatusEffect(StatusEffects.EFFECT_REGENERATE_SHOP))
        {
            this.writeLine(fileWriter, 0);

            return;
        }

        this.writeLine(fileWriter, 1);

        Vector<InventoryItemGroup> items = gameWorld.getShopInventory().getRandomItems();
        this.writeLine(fileWriter, items.size());

        for (int i = 0; i < items.size(); i++)
        {
            InventoryItemGroup itemGroup = items.get(i);

            this.writeLine(fileWriter, itemGroup.getItem().getItemNumber());
            this.writeLine(fileWriter, itemGroup.getNumItems());
        }

        Vector<EquipmentGroup> equipment = gameWorld.getShopInventory().getRandomEquipments();
        this.writeLine(fileWriter, equipment.size());

        for (int i = 0; i < equipment.size(); i++)
        {
            EquipmentGroup equipmentGroup = equipment.get(i);

            this.writeLine(fileWriter, equipmentGroup.getItem().getEquipmentNumber());
            this.writeLine(fileWriter, equipmentGroup.getNumItems());
        }
    }
}

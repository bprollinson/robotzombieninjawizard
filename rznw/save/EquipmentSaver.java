package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.map.GameWorld;

import java.io.BufferedWriter;

public class EquipmentSaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        MainCharacter mainCharacter = gameWorld.getMainCharacter();
        Equipment equipment = mainCharacter.getEquipment();

        this.writeLine(fileWriter, equipment.getNumGroups());

        for (int i = 0; i < equipment.getNumGroups(); i++)
        {
            EquipmentGroup equipmentGroup = equipment.getEquipmentGroup(i);

            this.writeLine(fileWriter, equipmentGroup.getItem().getEquipmentNumber());
            this.writeLine(fileWriter, equipmentGroup.getNumItems());
        }

        this.writeEquipmentIndex(fileWriter, equipment.getEquippedWeapon());
        this.writeEquipmentIndex(fileWriter, equipment.getEquippedShield());
        this.writeEquipmentIndex(fileWriter, equipment.getEquippedArmor());
    }

    private void writeEquipmentIndex(BufferedWriter fileWriter, EquipmentItem equippedItem)
    {
        int equippedEquipmentNumber = 0;

        if (equippedItem != null)
        {
            equippedEquipmentNumber = equippedItem.getEquipmentNumber();
        }

        this.writeLine(fileWriter, equippedEquipmentNumber);
    }
}

package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.map.GameWorld;

import java.io.BufferedWriter;

public class EquipmentSaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        MainCharacter mainCharacter = gameWorld.getMainCharacter();
        Equipment equipment = mainCharacter.getEquipment();

        this.writeLine(fileWriter, equipment.getNumWeaponGroups());

        for (int i = 0; i < equipment.getNumWeaponGroups(); i++)
        {
            EquipmentGroup weaponGroup = equipment.getWeaponGroup(i);

            this.writeLine(fileWriter, weaponGroup.getItem().getEquipmentNumber());
            this.writeLine(fileWriter, weaponGroup.getNumItems());
        }

        this.writeLine(fileWriter, equipment.getNumShieldGroups());

        for (int i = 0; i < equipment.getNumShieldGroups(); i++)
        {
            EquipmentGroup shieldGroup = equipment.getShieldGroup(i);

            this.writeLine(fileWriter, shieldGroup.getItem().getEquipmentNumber());
            this.writeLine(fileWriter, shieldGroup.getNumItems());
        }

        this.writeLine(fileWriter, equipment.getNumArmorGroups());

        for (int i = 0; i < equipment.getNumArmorGroups(); i++)
        {
            EquipmentGroup armorGroup = equipment.getArmorGroup(i);

            this.writeLine(fileWriter, armorGroup.getItem().getEquipmentNumber());
            this.writeLine(fileWriter, armorGroup.getNumItems());
        }
    }
}

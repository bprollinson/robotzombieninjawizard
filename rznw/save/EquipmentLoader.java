package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.EquipmentItemFactory;
import rznw.map.GameWorld;

import java.io.BufferedReader;

public class EquipmentLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        int numEquipmentGroups = this.readInteger(fileReader);
        System.out.println("Num equipment groups: " + numEquipmentGroups);

        for (int i = 0; i < numEquipmentGroups; i++)
        {
            int equipmentIndex = this.readInteger(fileReader);
            int equipmentQuantity = this.readInteger(fileReader);
            System.out.println("Equipment group: " + equipmentIndex + " - " + equipmentQuantity);

            this.addEquipment(gameWorld, equipmentIndex, equipmentQuantity);
        }

        int equippedWeaponIndex = this.readInteger(fileReader);
        System.out.println("Equipped weapon index: " + equippedWeaponIndex);
        this.equipWeapon(gameWorld, equippedWeaponIndex);

        int equippedShieldIndex = this.readInteger(fileReader);
        System.out.println("Equipped shield index: " + equippedShieldIndex);
        this.equipShield(gameWorld, equippedShieldIndex);

        int equippedArmorIndex = this.readInteger(fileReader);
        System.out.println("Equipped armor index: " + equippedArmorIndex);
        this.equipArmor(gameWorld, equippedArmorIndex);
    }

    private void addEquipment(GameWorld gameWorld, int equipmentIndex, int equipmentQuantity)
    {
        MainCharacter character = gameWorld.getMainCharacter();
        Equipment equipment = character.getEquipment();

        EquipmentItem equipmentItem = EquipmentItemFactory.factory(equipmentIndex);
        try
        {
            equipment.addEquipment(new EquipmentGroup(equipmentItem, equipmentQuantity));
        }
        catch (EquipmentFullException ife)
        {
        }
    }

    private void equipWeapon(GameWorld gameWorld, int equipmentIndex)
    {
        if (equipmentIndex == 0)
        {
            return;
        }

        MainCharacter mainCharacter = gameWorld.getMainCharacter();
        Equipment equipment = mainCharacter.getEquipment();

        for (int i = 0; i < equipment.getNumWeaponGroups(); i++)
        {
            EquipmentGroup equipmentGroup = equipment.getWeaponGroup(i);
            EquipmentItem equipmentItem = equipmentGroup.getItem();
            if (equipmentItem.getEquipmentNumber() == equipmentIndex)
            {
                equipment.equipWeapon(i);
                break;
            }
        }
    }

    private void equipShield(GameWorld gameWorld, int equipmentIndex)
    {
        if (equipmentIndex == 0)
        {
            return;
        }

        MainCharacter mainCharacter = gameWorld.getMainCharacter();
        Equipment equipment = mainCharacter.getEquipment();

        for (int i = 0; i < equipment.getNumShieldGroups(); i++)
        {
            EquipmentGroup equipmentGroup = equipment.getShieldGroup(i);
            EquipmentItem equipmentItem = equipmentGroup.getItem();
            if (equipmentItem.getEquipmentNumber() == equipmentIndex)
            {
                equipment.equipShield(i);
                break;
            }
        }
    }

    private void equipArmor(GameWorld gameWorld, int equipmentIndex)
    {
        if (equipmentIndex == 0)
        {
            return;
        }

        MainCharacter mainCharacter = gameWorld.getMainCharacter();
        Equipment equipment = mainCharacter.getEquipment();

        for (int i = 0; i < equipment.getNumArmorGroups(); i++)
        {
            EquipmentGroup equipmentGroup = equipment.getArmorGroup(i);
            EquipmentItem equipmentItem = equipmentGroup.getItem();
            if (equipmentItem.getEquipmentNumber() == equipmentIndex)
            {
                equipment.equipArmor(i);
                break;
            }
        }
    }
}

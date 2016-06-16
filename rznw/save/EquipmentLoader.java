package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.map.GameWorld;

import java.io.BufferedReader;

public class EquipmentLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        int numWeaponGroups = this.readInteger(fileReader);
        System.out.println("Num weapon groups: " + numWeaponGroups);

        for (int i = 0; i < numWeaponGroups; i++)
        {
            int weaponIndex = this.readInteger(fileReader);
            int weaponQuantity = this.readInteger(fileReader);
            System.out.println("Weapon group: " + weaponIndex + " - " + weaponQuantity);

            this.addEquipment(gameWorld, weaponIndex, weaponQuantity);
        }

        int numShieldGroups = this.readInteger(fileReader);
        System.out.println("Num shield groups: " + numShieldGroups);

        for (int i = 0; i < numShieldGroups; i++)
        {
            int shieldIndex = this.readInteger(fileReader);
            int shieldQuantity = this.readInteger(fileReader);
            System.out.println("Shield group: " + shieldIndex + " - " + shieldQuantity);

            this.addEquipment(gameWorld, shieldIndex, shieldQuantity);
        }

        int numArmorGroups = this.readInteger(fileReader);
        System.out.println("Num armor groups: " + numArmorGroups);

        for (int i = 0; i < numArmorGroups; i++)
        {
            int armorIndex = this.readInteger(fileReader);
            int armorQuantity = this.readInteger(fileReader);
            System.out.println("Armor group: " + armorIndex + " - " + armorQuantity);

            this.addEquipment(gameWorld, armorIndex, armorQuantity);
        }
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
}

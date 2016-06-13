package rznw.save;

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
            String weaponName = this.readLine(fileReader);
            int weaponQuantity = this.readInteger(fileReader);
            System.out.println("Weapon group: " + weaponName + " - " + weaponQuantity);
        }

        int numShieldGroups = this.readInteger(fileReader);
        System.out.println("Num shield groups: " + numShieldGroups);

        for (int i = 0; i < numShieldGroups; i++)
        {
            String shieldName = this.readLine(fileReader);
            int shieldQuantity = this.readInteger(fileReader);
            System.out.println("Shield group: " + shieldName + " - " + shieldQuantity);
        }

        int numArmorGroups = this.readInteger(fileReader);
        System.out.println("Num armor groups: " + numArmorGroups);

        for (int i = 0; i < numArmorGroups; i++)
        {
            String armorName = this.readLine(fileReader);
            int armorQuantity = this.readInteger(fileReader);
            System.out.println("Armor group: " + armorName + " - " + armorQuantity);
        }
    }
}

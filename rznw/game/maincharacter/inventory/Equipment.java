package rznw.game.maincharacter.inventory;

import java.util.Vector;

import rznw.game.maincharacter.MainCharacter;

public class Equipment
{
    private Vector<EquipmentGroup> equipmentGroups;
    private int equippedWeapon = -1;
    private int equippedShield = -1;
    private int equippedArmor = -1;
    private MainCharacter character;

    public Equipment(MainCharacter character)
    {
        this.equipmentGroups = new Vector<EquipmentGroup>();
        this.character = character;
    }

    public void addEquipment(EquipmentGroup equipmentGroup) throws EquipmentFullException
    {
        this.assertCanAddEquipment(equipmentGroup);

        int index = this.getEquipmentGroupPosition(equipmentGroup);

        if (index == -1)
        {
            this.equipmentGroups.add(equipmentGroup);
        }
        else
        {
            this.equipmentGroups.get(index).addEquipmentToGroup(equipmentGroup.getNumItems());
        }
    }

    public void removeEquipment(EquipmentItem item)
    {
        int index = this.getEquipmentGroupPosition(new EquipmentGroup(item, 1));

        if (index == -1)
        {
            return;
        }

        this.equipmentGroups.get(index).removeEquipmentFromGroup(1);

        if (this.equipmentGroups.get(index).getNumItems() == 0)
        {
            this.equipmentGroups.remove(index);
        }
    }

    private int getEquipmentGroupPosition(EquipmentGroup equipmentGroup)
    {
        int index = -1;

        for (int i = 0; i < this.equipmentGroups.size(); i++)
        {
            EquipmentGroup existingEquipmentGroup = this.equipmentGroups.get(i);
            if (equipmentGroup.getItem().getClass().equals(existingEquipmentGroup.getItem().getClass()))
            {
                index = i;
                break;
            }
        }

        return index;
    }

    public int getNumGroups()
    {
        return this.equipmentGroups.size();
    }

    public EquipmentGroup getEquipmentGroup(int groupIndex)
    {
        return this.equipmentGroups.get(groupIndex);
    }

    public int getNumWeaponGroups()
    {
        return this.getNumGroupsOfType(Weapon.EQUIPMENT_TYPE);
    }

    public EquipmentGroup getWeaponGroup(int targetPosition)
    {
        return this.getGroupOfType(Weapon.EQUIPMENT_TYPE, targetPosition);
    }

    public void unequipWeapon()
    {
        this.equippedWeapon = -1;
    }

    public void equipWeapon(int weaponGroupIndex)
    {
        this.equippedWeapon = weaponGroupIndex;
    }

    public Weapon getEquippedWeapon()
    {
        if (this.equippedWeapon == -1)
        {
            return null;
        }

        return (Weapon)this.getWeaponGroup(this.equippedWeapon).getItem();
    }

    public int getNumShieldGroups()
    {
        return this.getNumGroupsOfType(Shield.EQUIPMENT_TYPE);
    }

    public EquipmentGroup getShieldGroup(int targetPosition)
    {
        return this.getGroupOfType(Shield.EQUIPMENT_TYPE, targetPosition);
    }

    public void unequipShield()
    {
        this.equippedShield = -1;
    }

    public void equipShield(int shieldGroupIndex)
    {
        this.equippedShield = shieldGroupIndex;
    }

    public Shield getEquippedShield()
    {
        if (this.equippedShield == -1)
        {
            return null;
        }

        return (Shield)this.getShieldGroup(this.equippedShield).getItem();
    }

    public int getNumArmorGroups()
    {
        return this.getNumGroupsOfType(Armor.EQUIPMENT_TYPE);
    }

    public EquipmentGroup getArmorGroup(int targetPosition)
    {
        return this.getGroupOfType(Armor.EQUIPMENT_TYPE, targetPosition);
    }

    public void unequipArmor()
    {
        this.equippedArmor = -1;
    }

    public void equipArmor(int armorGroupIndex)
    {
        this.equippedArmor = armorGroupIndex;
    }

    public Armor getEquippedArmor()
    {
        if (this.equippedArmor == -1)
        {
            return null;
        }

        return (Armor)this.getArmorGroup(this.equippedArmor).getItem();
    }

    private void assertCanAddEquipment(EquipmentGroup equipmentGroup) throws EquipmentFullException
    {
        int statPoints = this.character.getStatPoints(MainCharacter.STAT_UNENCUMBERANCE);
        int maxSize = 1 + statPoints;

        int index = this.getEquipmentGroupPosition(equipmentGroup);

        if (index == -1 && this.equipmentGroups.size() >= maxSize)
        {
            throw new EquipmentFullException();
        }

        if (index != -1 && this.equipmentGroups.get(index).getNumItems() >= maxSize)
        {
            throw new EquipmentFullException();
        }
    }

    private int getNumGroupsOfType(int requiredType)
    {
        int result = 0;

        for (int i = 0; i < this.equipmentGroups.size(); i++)
        {
            EquipmentGroup equipmentGroup = this.equipmentGroups.get(i);
            if (equipmentGroup.getItem().getEquipmentType() == requiredType)
            {
                result++;
            }
        }

        return result;
    }

    private EquipmentGroup getGroupOfType(int requiredType, int targetPosition)
    {
        int pos = 0;

        for (int i = 0; i < this.equipmentGroups.size(); i++)
        {
            if (pos == targetPosition && this.equipmentGroups.get(i).getItem().getEquipmentType() == requiredType)
            {
                return this.equipmentGroups.get(i);
            }

            if (this.equipmentGroups.get(i).getItem().getEquipmentType() == requiredType)
            {
                pos++;
            }
        }

        return null;
    }
}

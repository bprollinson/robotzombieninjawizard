package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.stat.Stat;

import java.util.HashMap;
import java.util.Vector;

public class Equipment
{
    private MainCharacter character;
    private HashMap<Integer, Vector<EquipmentGroup>> equipmentGroups = new HashMap<Integer, Vector<EquipmentGroup>>();
    private HashMap<Integer, Integer> equippedEquipment = new HashMap<Integer, Integer>();

    public Equipment(MainCharacter character)
    {
        this.character = character;
        this.equipmentGroups.put(Weapon.EQUIPMENT_TYPE, new Vector<EquipmentGroup>());
        this.equipmentGroups.put(Shield.EQUIPMENT_TYPE, new Vector<EquipmentGroup>());
        this.equipmentGroups.put(Armor.EQUIPMENT_TYPE, new Vector<EquipmentGroup>());
        this.equippedEquipment.put(Weapon.EQUIPMENT_TYPE, -1);
        this.equippedEquipment.put(Shield.EQUIPMENT_TYPE, -1);
        this.equippedEquipment.put(Armor.EQUIPMENT_TYPE, -1);
    }

    public void addEquipment(EquipmentGroup equipmentGroup) throws EquipmentFullException
    {
        this.assertCanAddEquipment(equipmentGroup);

        int index = this.getEquipmentGroupPosition(equipmentGroup);
        int type = equipmentGroup.getItem().getEquipmentType();

        if (index == -1)
        {
            this.equipmentGroups.get(type).add(equipmentGroup);
        }
        else
        {
            this.equipmentGroups.get(type).get(index).addEquipmentToGroup(equipmentGroup.getNumItems());
        }
    }

    public void removeEquipment(EquipmentItem item)
    {
        int index = this.getEquipmentGroupPosition(new EquipmentGroup(item, 1));
        int type = item.getEquipmentType();

        if (index == -1)
        {
            return;
        }

        this.equipmentGroups.get(type).get(index).removeEquipmentFromGroup(1);

        if (this.equipmentGroups.get(type).get(index).getNumItems() == 0)
        {
            this.equipmentGroups.get(type).remove(index);

            int equippedIndex = this.equippedEquipment.get(type);
            if (equippedIndex != -1 && equippedIndex > index)
            {
                this.equippedEquipment.put(type, equippedIndex - 1);
            }
        }
    }

    private int getEquipmentGroupPosition(EquipmentGroup equipmentGroup)
    {
        int index = -1;
        int type = equipmentGroup.getItem().getEquipmentType();

        for (int i = 0; i < this.equipmentGroups.get(type).size(); i++)
        {
            EquipmentGroup existingEquipmentGroup = this.equipmentGroups.get(type).get(i);
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
        return this.equipmentGroups.get(Weapon.EQUIPMENT_TYPE).size() + this.equipmentGroups.get(Shield.EQUIPMENT_TYPE).size() + this.equipmentGroups.get(Armor.EQUIPMENT_TYPE).size();
    }

    public EquipmentGroup getEquipmentGroup(int groupIndex)
    {
        int numWeapons = this.getNumWeaponGroups();
        int numShields = this.getNumShieldGroups();
        int numArmor = this.getNumArmorGroups();

        if (groupIndex < numWeapons)
        {
            return this.getWeaponGroup(groupIndex);
        }
        groupIndex -= numWeapons;

        if (groupIndex < numShields)
        {
            return this.getShieldGroup(groupIndex);
        }
        groupIndex -= numShields;

        return this.getArmorGroup(groupIndex);
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
        this.equippedEquipment.put(Weapon.EQUIPMENT_TYPE, -1);
    }

    public void equipWeapon(int weaponGroupIndex)
    {
        this.equippedEquipment.put(Weapon.EQUIPMENT_TYPE, weaponGroupIndex);
    }

    public Weapon getEquippedWeapon()
    {
        return (Weapon)this.getEquippedItemOfType(Weapon.EQUIPMENT_TYPE);
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
        this.equippedEquipment.put(Shield.EQUIPMENT_TYPE, -1);
    }

    public void equipShield(int shieldGroupIndex)
    {
        this.equippedEquipment.put(Shield.EQUIPMENT_TYPE, shieldGroupIndex);
    }

    public Shield getEquippedShield()
    {
        return (Shield)this.getEquippedItemOfType(Shield.EQUIPMENT_TYPE);
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
        this.equippedEquipment.put(Armor.EQUIPMENT_TYPE, -1);
    }

    public void equipArmor(int armorGroupIndex)
    {
        this.equippedEquipment.put(Armor.EQUIPMENT_TYPE, armorGroupIndex);
    }

    public Armor getEquippedArmor()
    {
        return (Armor)this.getEquippedItemOfType(Armor.EQUIPMENT_TYPE);
    }

    private void assertCanAddEquipment(EquipmentGroup equipmentGroup) throws EquipmentFullException
    {
        if (this.character == null)
        {
            return;
        }

        int statPoints = this.character.getStats().getStatPoints(Stat.STAT_UNENCUMBERANCE);
        int maxSize = 1 + statPoints;

        int index = this.getEquipmentGroupPosition(equipmentGroup);

        if (index == -1 && this.getNumGroups() >= maxSize)
        {
            throw new EquipmentFullException();
        }

        if (index != -1 && this.equipmentGroups.get(equipmentGroup.getItem().getEquipmentType()).get(index).getNumItems() >= maxSize)
        {
            throw new EquipmentFullException();
        }
    }

    private int getNumGroupsOfType(int requiredType)
    {
        return this.equipmentGroups.get(requiredType).size();
    }

    private EquipmentGroup getGroupOfType(int requiredType, int targetPosition)
    {
        return this.equipmentGroups.get(requiredType).get(targetPosition);
    }

    public EquipmentItem getEquippedItemOfType(int type)
    {
        if (this.equippedEquipment.get(type) == -1)
        {
            return null;
        }

        return this.getGroupOfType(type, this.equippedEquipment.get(type)).getItem();
    }
}

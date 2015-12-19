package rznw.game.maincharacter.inventory;

import java.util.Vector;

public class Equipment
{
    Vector<EquipmentGroup> equipmentGroups;
    private int equippedWeapon = -1;
    private int equippedShield = -1;

    public Equipment()
    {
        this.equipmentGroups = new Vector<EquipmentGroup>();
    }

    public void addEquipment(EquipmentGroup equipmentGroup)
    {
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

    public int getNumWeaponGroups()
    {
        int result = 0;

        for (int i = 0; i < this.equipmentGroups.size(); i++)
        {
            EquipmentGroup equipmentGroup = this.equipmentGroups.get(i);
            if (equipmentGroup.getItem() instanceof Weapon)
            {
                result++;
            }
        }

        return result;
    }

    public EquipmentGroup getWeaponGroup(int targetPosition)
    {
        int pos = 0;

        for (int i = 0; i < this.equipmentGroups.size(); i++)
        {
            if (pos == targetPosition && this.equipmentGroups.get(i).getItem() instanceof Weapon)
            {
                return this.equipmentGroups.get(i);
            }

            if (this.equipmentGroups.get(i).getItem() instanceof Weapon)
            {
                pos++;
            }
        }

        return null;
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
        int result = 0;

        for (int i = 0; i < this.equipmentGroups.size(); i++)
        {
            EquipmentGroup equipmentGroup = this.equipmentGroups.get(i);
            if (equipmentGroup.getItem() instanceof Shield)
            {
                result++;
            }
        }

        return result;
    }

    public EquipmentGroup getShieldGroup(int targetPosition)
    {
        int pos = 0;

        for (int i = 0; i < this.equipmentGroups.size(); i++)
        {
            if (pos == targetPosition && this.equipmentGroups.get(i).getItem() instanceof Shield)
            {
                return this.equipmentGroups.get(i);
            }

            if (this.equipmentGroups.get(i).getItem() instanceof Shield)
            {
                pos++;
            }
        }

        return null;
    }

    public void unequipShield()
    {
        this.equippedShield = -1;
    }

    public void equipShield(int shieldGroupIndex)
    {
        this.equippedShield = shieldGroupIndex;
    }

    public EquipmentItem getEquippedShield()
    {
        if (this.equippedShield == -1)
        {
            return null;
        }

        return this.getShieldGroup(this.equippedShield).getItem();
    }
}

package rznw.game.maincharacter.inventory;

import java.util.Vector;

import rznw.game.maincharacter.MainCharacter;

public class Equipment
{
    Vector<EquipmentGroup> equipmentGroups;
    private int equippedWeapon = -1;
    private int equippedShield = -1;
    private int equippedArmor = -1;
    MainCharacter character;

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

    public int getNumWeaponGroups()
    {
        int result = 0;

        for (int i = 0; i < this.equipmentGroups.size(); i++)
        {
            EquipmentGroup equipmentGroup = this.equipmentGroups.get(i);
            if (equipmentGroup.getItem().isWeapon())
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
            if (pos == targetPosition && this.equipmentGroups.get(i).getItem().isWeapon())
            {
                return this.equipmentGroups.get(i);
            }

            if (this.equipmentGroups.get(i).getItem().isWeapon())
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
        int result = 0;

        for (int i = 0; i < this.equipmentGroups.size(); i++)
        {
            EquipmentGroup equipmentGroup = this.equipmentGroups.get(i);
            if (equipmentGroup.getItem() instanceof Armor)
            {
                result++;
            }
        }

        return result;
    }

    public EquipmentGroup getArmorGroup(int targetPosition)
    {
        int pos = 0;

        for (int i = 0; i < this.equipmentGroups.size(); i++)
        {
            if (pos == targetPosition && this.equipmentGroups.get(i).getItem() instanceof Armor)
            {
                return this.equipmentGroups.get(i);
            }

            if (this.equipmentGroups.get(i).getItem() instanceof Armor)
            {
                pos++;
            }
        }

        return null;
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
        int statPoints = this.character.getStatPoints(10);
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
}

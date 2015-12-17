package rznw.game.maincharacter.inventory;

import java.util.Vector;

public class Equipment
{
    Vector<EquipmentGroup> equipmentGroups;

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
}

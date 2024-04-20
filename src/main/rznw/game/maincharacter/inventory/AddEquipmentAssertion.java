package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.stat.Stat;

public class AddEquipmentAssertion
{
    private MainCharacter character;

    public AddEquipmentAssertion(MainCharacter character)
    {
        this.character = character;
    }

    public void validate(EquipmentGroup equipmentGroup) throws EquipmentFullException
    {
        if (this.character == null)
        {
            return;
        }

        int statPoints = this.character.getStats().getStatPoints(Stat.STAT_UNENCUMBERANCE);
        int maxSize = 1 + statPoints;

        Equipment equipment = character.getEquipment();
        int index = equipment.getEquipmentGroupPosition(equipmentGroup);

        if (index == -1 && equipment.getNumGroups() >= maxSize)
        {
            throw new EquipmentFullException();
        }

        if (index != -1 && equipment.getGroupOfType(equipmentGroup.getItem().getEquipmentType(), index).getNumItems() >= maxSize)
        {
            throw new EquipmentFullException();
        }
    }
}

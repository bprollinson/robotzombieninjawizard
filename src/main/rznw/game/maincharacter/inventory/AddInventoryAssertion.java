package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.stat.Stat;

public class AddInventoryAssertion
{
    private MainCharacter character;

    public AddInventoryAssertion(MainCharacter character)
    {
        this.character = character;
    }

    public void validate(InventoryItemGroup itemGroup) throws InventoryFullException
    {
        if (this.character == null)
        {
            return;
        }

        int statPoints = this.character.getStats().getStatPoints(Stat.STAT_UNENCUMBERANCE);
        int maxSize = 1 + statPoints;

        Inventory inventory = this.character.getInventory();
        int index = inventory.getItemGroupPosition(itemGroup);

        if (index == -1 && inventory.getNumItemGroups() >= maxSize)
        {
            throw new InventoryFullException();
        }

        if (index != -1 && inventory.getItemGroup(index).getNumItems() >= maxSize)
        {
            throw new InventoryFullException();
        }
    }
}

package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.map.GameWorld;

import java.io.BufferedWriter;

public class InventorySaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        MainCharacter mainCharacter = gameWorld.getMainCharacter();
        Inventory inventory = mainCharacter.getInventory();

        this.writeLine(fileWriter, inventory.getNumGold());

        this.writeLine(fileWriter, inventory.getNumItemGroups());

        for (int i = 0; i < inventory.getNumItemGroups(); i++)
        {
            InventoryItemGroup itemGroup = inventory.getItemGroup(i);

            this.writeLine(fileWriter, itemGroup.getItem().getDisplayName());
            this.writeLine(fileWriter, itemGroup.getNumItems());
        }
    }
}

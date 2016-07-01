package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryFullException;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.InventoryItemFactory;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.map.GameWorld;

import java.io.BufferedReader;

public class InventoryLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        MainCharacter character = gameWorld.getMainCharacter();
        Inventory inventory = character.getInventory();

        int numGold = this.readInteger(fileReader);
        inventory.setNumGold(numGold);

        int numInventoryGroups = this.readInteger(fileReader);
        System.out.println("Num inventory groups: " + numInventoryGroups);

        for (int i = 0; i < numInventoryGroups; i++)
        {
            int itemIndex = this.readInteger(fileReader);
            int itemQuantity = this.readInteger(fileReader);
            System.out.println("Inventory group: " + itemIndex + " - " + itemQuantity);

            InventoryItem item = InventoryItemFactory.factory(itemIndex);
            try
            {
                inventory.addItems(new InventoryItemGroup(item, itemQuantity));
            }
            catch (InventoryFullException ife)
            {
            }
        }
    }
}

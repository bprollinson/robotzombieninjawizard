package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Inventory;
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
            String itemName = this.readLine(fileReader);
            int itemQuantity = this.readInteger(fileReader);
            System.out.println("Inventory group: " + itemName + " - " + itemQuantity);      
        }
    }
}

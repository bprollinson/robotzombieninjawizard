package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

import java.io.BufferedReader;

public class NewGamePlusShopLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        int status = this.readInteger(fileReader);

        if (status == 0)
        {
            return;
        }

        int numItemGroups = this.readInteger(fileReader);
        for (int i = 0; i < numItemGroups; i++)
        {
            int itemNumber = this.readInteger(fileReader);
            int numItems = this.readInteger(fileReader);
        }

        int numEquipmentGroups = this.readInteger(fileReader);
        for (int i = 0; i < numEquipmentGroups; i++)
        {
            int equipmentNumber = this.readInteger(fileReader);
            int numEquipment = this.readInteger(fileReader);
        }

        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().enableRegenerateShop();
    }
}

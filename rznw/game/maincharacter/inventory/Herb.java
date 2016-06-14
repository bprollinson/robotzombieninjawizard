package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class Herb extends InventoryItem
{
    private static int ITEM_NUMBER = 4;

    public String getDisplayName()
    {
        return "Herb";
    }

    public String getDescription()
    {
        return "Cures poison.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        character.getStatusEffects().healPoison();
    }

    public int getValue()
    {
        return 50;
    }

    public int getItemNumber()
    {
        return Herb.ITEM_NUMBER;
    }
}

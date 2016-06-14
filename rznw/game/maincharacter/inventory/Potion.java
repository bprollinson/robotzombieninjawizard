package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class Potion extends InventoryItem
{
    private static int ITEM_NUMBER = 7;

    public String getDisplayName()
    {
        return "Potion";
    }

    public String getDescription()
    {
        return "Heals 50 HP.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        character.heal(50);
    }

    public int getValue()
    {
        return 100;
    }

    public int getItemNumber()
    {
        return Potion.ITEM_NUMBER;
    }
}

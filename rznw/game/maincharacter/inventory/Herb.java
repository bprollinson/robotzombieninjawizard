package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public class Herb extends InventoryItem
{
    public String getDisplayName()
    {
        return "Herb";
    }

    public void useOnCharacter(MainCharacter character)
    {
        System.out.println("TODO: Use herb on character");
    }
}

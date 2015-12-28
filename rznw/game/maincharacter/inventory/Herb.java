package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public class Herb extends InventoryItem
{
    public String getDisplayName()
    {
        return "Herb";
    }

    public String getDescription()
    {
        return "Cures poison.";
    }

    public void useOnCharacter(MainCharacter character)
    {
        character.getStatusEffects().healPoison();
    }
}

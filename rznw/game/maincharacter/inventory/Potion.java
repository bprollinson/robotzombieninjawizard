package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public class Potion extends InventoryItem
{
    public String getDisplayName()
    {
        return "Potion";
    }

    public String getDescription()
    {
        return "Heals 50 HP.";
    }

    public void useOnCharacter(MainCharacter character)
    {
        character.heal(50);
    }
}

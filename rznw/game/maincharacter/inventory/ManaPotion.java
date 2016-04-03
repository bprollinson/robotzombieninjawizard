package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public class ManaPotion extends InventoryItem
{
    public String getDisplayName()
    {
        return "Mana Potion";
    }

    public String getDescription()
    {
        return "Heals 50 MP.";
    }

    public void useOnCharacter(MainCharacter character)
    {
        character.healMP(50);
    }

    public int getValue()
    {
        return 100;
    }
}

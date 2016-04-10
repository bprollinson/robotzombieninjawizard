package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public class FullManaPotion extends InventoryItem
{
    public String getDisplayName()
    {
        return "Full Mana Potion";
    }

    public String getDescription()
    {
        return "Heals to full MP.";
    }

    public void useOnCharacter(MainCharacter character)
    {
        character.setMP(character.getMaxMP());
    }

    public int getValue()
    {
        return 200;
    }
}

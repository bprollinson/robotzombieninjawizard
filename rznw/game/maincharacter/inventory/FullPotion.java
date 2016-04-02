package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public class FullPotion extends InventoryItem
{
    public String getDisplayName()
    {
        return "Full Potion";
    }

    public String getDescription()
    {
        return "Heals to full HP.";
    }

    public void useOnCharacter(MainCharacter character)
    {
        character.setHP(character.getMaxHP());
    }

    public int getValue()
    {
        return 200;
    }
}

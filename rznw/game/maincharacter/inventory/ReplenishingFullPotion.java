package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.utility.RandomNumberGenerator;

public class ReplenishingFullPotion extends FullPotion
{
    private static int ITEM_NUMBER = 9;

    public String getDisplayName()
    {
        return "Replenishing Full Potion";
    }

    public String getDescription()
    {
        return "Heals to full HP and has a chance to replenish.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        super.useOnCharacter(character, gameWorld);

        if (RandomNumberGenerator.rollSucceeds(25))
        {
            try
            {
                character.getInventory().addItems(new InventoryItemGroup(new ReplenishingFullPotion(), 1));
            }
            catch (InventoryFullException ife)
            {
            }
        }
    }

    public int getValue()
    {
        return 400;
    }

    public int getItemNumber()
    {
        return ReplenishingFullPotion.ITEM_NUMBER;
    }
}

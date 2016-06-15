package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.utility.RandomNumberGenerator;

public class ReplenishingSanityDrop extends SanityDrop
{
    public static final int ITEM_NUMBER = 11;

    public String getDisplayName()
    {
        return "Replenishing Sanity Drop";
    }

    public String getDescription()
    {
        return "Cures confusion and has a chance to replenish.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        super.useOnCharacter(character, gameWorld);

        if (RandomNumberGenerator.rollSucceeds(25))
        {
            try
            {
                character.getInventory().addItems(new InventoryItemGroup(new ReplenishingSanityDrop(), 1));
            }
            catch (InventoryFullException ife)
            {
            }
        }
    }

    public int getValue()
    {
        return 100;
    }

    public int getItemNumber()
    {
        return ReplenishingSanityDrop.ITEM_NUMBER;
    }
}

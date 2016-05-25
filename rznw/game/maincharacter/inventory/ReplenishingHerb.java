package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.utility.RandomNumberGenerator;

public class ReplenishingHerb extends Herb
{
    public String getDisplayName()
    {
        return "Replenishing Herb";
    }

    public String getDescription()
    {
        return "Cures poison and has a chance to replenish.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        super.useOnCharacter(character, gameWorld);

        if (RandomNumberGenerator.rollSucceeds(25))
        {
            try
            {
                character.getInventory().addItems(new InventoryItemGroup(new ReplenishingHerb(), 1));
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
}

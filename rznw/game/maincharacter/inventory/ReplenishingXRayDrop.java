package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.utility.RandomNumberGenerator;

public class ReplenishingXRayDrop extends XRayDrop
{
    public String getDisplayName()
    {
        return "Replenishing X-Ray Drop";
    }

    public String getDescription()
    {
        return "Reveals the entire map and has a chance to replenish.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        super.useOnCharacter(character, gameWorld);

        if (RandomNumberGenerator.rollSucceeds(25))
        {
            try
            {
                character.getInventory().addItems(new InventoryItemGroup(new ReplenishingXRayDrop(), 1));
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
}

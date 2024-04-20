package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class ReplenishingXRayDrop extends InventoryItem
{
    public static final int ITEM_NUMBER = 12;

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
        LogRendererFactory.instance().log("Using replenishing x-ray drop.");

        Map map = gameWorld.getMap();

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                map.setVisible(character, row, column);
            }
        }

        LogRendererFactory.instance().log("Revealed the entire map.");

        if (RandomNumberGenerator.rollSucceeds(25))
        {
            try
            {
                character.getInventory().addItems(new InventoryItemGroup(new ReplenishingXRayDrop(), 1));
                LogRendererFactory.instance().log("Replenished drop.");
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
        return ReplenishingXRayDrop.ITEM_NUMBER;
    }
}

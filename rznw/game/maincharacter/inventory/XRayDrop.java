package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;

public class XRayDrop extends InventoryItem
{
    public static final int ITEM_NUMBER = 14;

    public String getDisplayName()
    {
        return "X-Ray Drop";
    }

    public String getDescription()
    {
        return "Reveals the entire map.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        Map map = gameWorld.getMap();

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                map.setVisible(character, row, column);
            }
        }
    }

    public int getValue()
    {
        return 200;
    }

    public int getItemNumber()
    {
        return XRayDrop.ITEM_NUMBER;
    }
}

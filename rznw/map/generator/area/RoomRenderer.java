package rznw.map.generator.area;

import rznw.map.Map;
import rznw.map.element.Void;
import rznw.map.element.Wall;

public class RoomRenderer
{
    public void renderVoid(Map map)
    {
        for (int i = 0; i < Map.NUM_ROWS; i++)
        {
            for (int j = 0; j < Map.NUM_COLUMNS; j++)
            {
                map.setElement(i, j, new Void(i, j));
            }
        }
    }

    public void renderRoom(Map map, int startX, int startY, int endX, int endY)
    {
        for (int i = startX; i <= endX; i++)
        {
            map.setElement(startY, i, new Wall(startY, i));
            map.setElement(endY, i, new Wall(endY, i));
        }

        for (int i = startY; i <= endY; i++)
        {
            map.setElement(i, startX, new Wall(i, startX));
            map.setElement(i, endX, new Wall(i, endX));
        }

        for (int i = startY + 1; i < endY; i++)
        {
            for (int j = startX + 1; j < endX; j++)
            {
                map.setElement(i, j, null);
            }
        }
    }
}

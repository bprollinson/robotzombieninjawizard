package rznw.map.generator;

import rznw.map.Map;
import rznw.map.element.Wall;

public class RoomRenderer
{
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
    }
}

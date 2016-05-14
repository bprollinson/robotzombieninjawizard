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

    public void renderRoom(Map map, int startRow, int startColumn, int endRow, int endColumn)
    {
        for (int i = startColumn; i <= endColumn; i++)
        {
            map.setElement(startRow, i, new Wall(startRow, i));
            map.setElement(endRow, i, new Wall(endRow, i));
        }

        for (int i = startRow; i <= endRow; i++)
        {
            map.setElement(i, startColumn, new Wall(i, startColumn));
            map.setElement(i, endColumn, new Wall(i, endColumn));
        }

        for (int i = startRow + 1; i < endRow; i++)
        {
            for (int j = startColumn + 1; j < endColumn; j++)
            {
                map.setElement(i, j, null);
            }
        }
    }
}

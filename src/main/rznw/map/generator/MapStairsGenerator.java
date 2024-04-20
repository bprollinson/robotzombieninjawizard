package rznw.map.generator;

import rznw.map.Map;
import rznw.map.element.Stairs;
import rznw.map.generator.area.MapArea;
import rznw.utility.RandomNumberGenerator;

import java.util.List;

public class MapStairsGenerator
{
    public void placeStairs(Map map, List<MapArea> rooms)
    {
        if (map.isLastLevel())
        {
            return;
        }

        int roomIndex = RandomNumberGenerator.randomInteger(0, rooms.size() - 1);
        MapArea room = rooms.get(roomIndex);
        int posX = RandomNumberGenerator.randomInteger(room.getStartColumn() + 1, room.getEndColumn() - 1);
        int posY = RandomNumberGenerator.randomInteger(room.getStartRow() + 1, room.getEndRow() - 1);

        map.setBackgroundElement(posY, posX, new Stairs(posY, posX));
    }
}

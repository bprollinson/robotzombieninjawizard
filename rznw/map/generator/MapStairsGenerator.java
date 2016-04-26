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
        int posX = RandomNumberGenerator.randomInteger(room.getStartX() + 1, room.getEndX() - 1);
        int posY = RandomNumberGenerator.randomInteger(room.getStartY() + 1, room.getEndY() - 1);

        map.setBackgroundElement(posY, posX, new Stairs(posY, posX));
    }
}

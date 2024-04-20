package rznw.map.generator.area;

import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.Wall;

import java.util.List;

public class MapAreaCollision
{
    public boolean wallExistsWithinRectangle(Map map, MapArea openArea)
    {
        for (int row = openArea.getStartRow(); row <= openArea.getEndRow(); row++)
        {
            for (int column = openArea.getStartColumn(); column <= openArea.getEndColumn(); column++)
            {
                MapElement element = map.getElement(row, column);
                if (element instanceof Wall)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean areaFallsWithinAnotherArea(MapArea openArea, List<MapArea> rooms)
    {
        for (int i = 0; i < rooms.size(); i++)
        {
            MapArea room = rooms.get(i);

            if (openArea.fallsWithin(room))
            {
                return true;
            }
        }

        return false;
    }
}

package rznw.map.generator.area;

import rznw.map.Map;

import java.util.List;

public class MapAreaCollision
{
    public boolean elementExistsWithinRectangle(Map map, MapArea openArea)
    {
        for (int row = openArea.getStartY(); row <= openArea.getEndY(); row++)
        {
            for (int column = openArea.getStartX(); column <= openArea.getEndX(); column++)
            {
                if (map.getElement(row, column) != null)
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

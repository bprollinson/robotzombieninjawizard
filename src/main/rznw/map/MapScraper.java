package rznw.map;

import rznw.map.element.MapElement;

public class MapScraper
{
    public boolean mapContainsElementOfType(Map map, Class clazz)
    {
        return this.getFirstElementOfType(map, clazz) != null;
    }

    public MapElement getFirstElementOfType(Map map, Class clazz)
    {
        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getElement(row, column);

                if (element != null && clazz.isInstance(element))
                {
                    return element;
                }
            }
        }

        return null;
    }

    public boolean mapContainsBackgroundElementOfType(Map map, Class clazz)
    {
        return this.getFirstBackgroundElementOfType(map, clazz) != null;
    }

    public MapElement getFirstBackgroundElementOfType(Map map, Class clazz)
    {
        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getBackgroundElement(row, column);

                if (element != null && clazz.isInstance(element))
                {
                    return element;
                }
            }
        }

        return null;
    }
}

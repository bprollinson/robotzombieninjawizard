package rznw.map;

import rznw.map.element.MapElement;

public class MapElementSetter
{
    public static void setElement(Map map, MapElement element, int row, int column)
    {
        element.setRow(row);
        element.setColumn(column);
        map.setElement(row, column, element);
    }
}

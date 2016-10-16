package rznw.map;

import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;

public class MapRayTracer
{
    private Map map;

    public MapRayTracer(Map map)
    {
        this.map = map;
    }

    public MapElement findNextElementInDirection(MapElement startingPositionElement, int direction)
    {
        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(0, 0, direction);

        int row = startingPositionElement.getRow();
        int column = startingPositionElement.getColumn();

        do
        {
            row += positionChange.getDeltaRow();
            column += positionChange.getDeltaColumn();
        }
        while (this.map.getElement(row, column) == null);

        return this.map.getElement(row, column);
    }
}

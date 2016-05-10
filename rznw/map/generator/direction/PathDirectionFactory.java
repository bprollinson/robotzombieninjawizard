package rznw.map.generator.direction;

import rznw.map.generator.area.MapArea;

import java.util.Vector;

public class PathDirectionFactory
{
    public static PathDirection[] getFromRooms(MapArea startRoom, MapArea endRoom)
    {
        Vector<PathDirection> result = new Vector<PathDirection>();

        if (startRoom.getEndX() < endRoom.getStartX())
        {
            result.add(new PathDirectionRight());
        }

        if (startRoom.getStartX() > endRoom.getEndX())
        {
            result.add(new PathDirectionLeft());
        }

        if (startRoom.getEndY() < endRoom.getStartY())
        {
            result.add(new PathDirectionDown());
        }

        if (startRoom.getStartY() > endRoom.getEndY())
        {
            result.add(new PathDirectionUp());
        }

        return result.toArray(new PathDirection[result.size()]);
    }

    public static PathDirection[] getOppositeDirections(PathDirection[] direction)
    {
        PathDirection[] oppositeDirection = new PathDirection[direction.length];

        for (int i = 0; i < direction.length; i++)
        {
            oppositeDirection[i] = direction[i].getOppositeDirection();
        }

        return oppositeDirection;
    }
}

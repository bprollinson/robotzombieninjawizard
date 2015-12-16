package rznw.map;

import rznw.map.element.Void;
import rznw.map.element.Wall;
import rznw.map.generator.MapPoint;
import rznw.map.generator.direction.PathDirection;
import rznw.map.generator.direction.PathDirectionDown;
import rznw.map.generator.direction.PathDirectionLeft;
import rznw.map.generator.direction.PathDirectionRight;
import rznw.map.generator.direction.PathDirectionUp;
import rznw.map.generator.path.MapPath;
import rznw.map.generator.path.MapPathCache;

import java.util.Vector;

public class ShortestPathCalculator
{
    private Map map;
    private boolean voidWalk;

    public ShortestPathCalculator(Map map, boolean voidWalk)
    {
        this.map = map;
        this.voidWalk = voidWalk;
    }

    public MapPath calculateShortestPath(MapPoint startPoint, MapPoint endPoint)
    {
        MapPathCache pathCache = new MapPathCache();
        MapPath result = new MapPath(startPoint);

        return this.calculateShortestPath(this.map, endPoint, new MapPath[]{result}, pathCache, this.voidWalk);
    }

    private MapPath calculateShortestPath(Map map, MapPoint endPoint, MapPath[] paths, MapPathCache pathCache, boolean voidWalk)
    {
        if (paths.length == 0)
        {
            return null;
        }

        for (int i = 0; i < paths.length; i++)
        {
            MapPath path = paths[i];
            MapPoint currentPoint = path.getCurrentPoint();

            if (currentPoint.equals(endPoint))
            {
                return path;
            }
        }

        Vector<MapPath> newPaths = new Vector<MapPath>();

        for (int i = 0; i < paths.length; i++)
        {
            pathCache.registerPathAsUsed(paths[i]);
        }

        PathDirection[] possibleDirections = new PathDirection[]{
            new PathDirectionUp(),
            new PathDirectionDown(),
            new PathDirectionLeft(),
            new PathDirectionRight()
        };

        for (int i = 0; i < paths.length; i++)
        {
            for (int j = 0; j < possibleDirections.length; j++)
            {
                PathDirection possibleDirection = possibleDirections[j];
                MapPath path = paths[i].getSubsequentPath(possibleDirection);
                MapPoint point = path.getCurrentPoint();

                if (point.getY() >= 0 && point.getY() < Map.NUM_ROWS && point.getX() >= 0 && point.getX() < Map.NUM_COLUMNS && !(map.getElement(point.getY(), point.getX()) instanceof Wall) || path.getCurrentPoint().equals(endPoint))
                {
                    if (voidWalk || !(map.getElement(point.getY(), point.getX()) instanceof Void))
                    {
                        newPaths.add(path);
                    }
                }
            }
        }

        Vector<MapPath> uniquePaths = ShortestPathCalculator.makePathsUnique(newPaths);

        return this.calculateShortestPath(map, endPoint, uniquePaths.toArray(new MapPath[uniquePaths.size()]), pathCache, voidWalk);
    }

    private static Vector<MapPath> makePathsUnique(Vector<MapPath> paths)
    {
        Vector<MapPath> result = new Vector<MapPath>();

        for (int i = 0; i < paths.size(); i++)
        {
            MapPath path = paths.get(i);

            boolean found = false;

            for (int j = 0; j < result.size(); j++)
            {
                MapPath solutionPath = result.get(j);
                if (path.getCurrentPoint().equals(solutionPath.getCurrentPoint()))
                {
                    found = true;
                    break;
                }
            }

            if (!found)
            {
                result.add(path);
            }
        }

        return result;
    }
}

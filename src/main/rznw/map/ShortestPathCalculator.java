package rznw.map;

import rznw.map.element.CharacterMapElement;
import rznw.map.element.Void;
import rznw.map.element.Wall;
import rznw.map.generator.MapPoint;
import rznw.map.generator.direction.PathDirection;
import rznw.map.generator.direction.PathDirectionDown;
import rznw.map.generator.direction.PathDirectionLeft;
import rznw.map.generator.direction.PathDirectionRight;
import rznw.map.generator.direction.PathDirectionUp;
import rznw.map.generator.direction.PathDirectionUpLeft;
import rznw.map.generator.direction.PathDirectionUpRight;
import rznw.map.generator.direction.PathDirectionDownLeft;
import rznw.map.generator.direction.PathDirectionDownRight;
import rznw.map.generator.path.MapPath;
import rznw.map.generator.path.MapPathCache;

import java.util.Vector;

public class ShortestPathCalculator
{
    private Map map;
    private boolean voidWalk;
    private boolean allowDiagonal;

    public ShortestPathCalculator(Map map, boolean voidWalk, boolean allowDiagonal)
    {
        this.map = map;
        this.voidWalk = voidWalk;
        this.allowDiagonal = allowDiagonal;
    }

    public MapPath calculateShortestPath(MapPoint startPoint, MapPoint endPoint, boolean passThroughCharacters)
    {
        MapPathCache pathCache = new MapPathCache();
        MapPath result = new MapPath(startPoint);

        return this.calculateShortestPath(this.map, endPoint, new MapPath[]{result}, pathCache, passThroughCharacters);
    }

    private MapPath calculateShortestPath(Map map, MapPoint endPoint, MapPath[] paths, MapPathCache pathCache, boolean passThroughCharacters)
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

        if (this.allowDiagonal)
        {
            possibleDirections = new PathDirection[]{
                new PathDirectionUp(),
                new PathDirectionDown(),
                new PathDirectionLeft(),
                new PathDirectionRight(),
                new PathDirectionUpLeft(),
                new PathDirectionUpRight(),
                new PathDirectionDownLeft(),
                new PathDirectionDownRight()
            };
        }

        for (int i = 0; i < paths.length; i++)
        {
            for (int j = 0; j < possibleDirections.length; j++)
            {
                PathDirection possibleDirection = possibleDirections[j];
                MapPath path = paths[i].getSubsequentPath(possibleDirection);
                MapPoint point = path.getCurrentPoint();

                if (!pathCache.pathIsUsed(path) && this.pointReachable(point, endPoint, passThroughCharacters)) {
                    newPaths.add(path);
                }
            }
        }

        Vector<MapPath> uniquePaths = ShortestPathCalculator.makePathsUnique(newPaths);

        return this.calculateShortestPath(map, endPoint, uniquePaths.toArray(new MapPath[uniquePaths.size()]), pathCache, passThroughCharacters);
    }

    private boolean pointReachable(MapPoint point, MapPoint endPoint, boolean passThroughCharacters)
    {
        if (point.equals(endPoint)) {
            return true;
        }

        if (point.getRow() < 0 || point.getRow() >= Map.NUM_ROWS)
        {
            return false;
        }

        if (point.getColumn() < 0 || point.getColumn() >= Map.NUM_COLUMNS)
        {
            return false;
        }

        if (map.getElement(point.getRow(), point.getColumn()) instanceof Wall)
        {
            return false;
        }

        if (map.getElement(point.getRow(), point.getColumn()) instanceof Void && !this.voidWalk)
        {
            return false;
        }

        if (map.getElement(point.getRow(), point.getColumn()) instanceof CharacterMapElement && !passThroughCharacters)
        {
            return false;
        }

        return true;
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

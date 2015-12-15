package rznw.map;

import rznw.map.element.Void;
import rznw.map.element.Wall;
import rznw.map.generator.MapPoint;
import rznw.map.generator.direction.PathDirectionDown;
import rznw.map.generator.direction.PathDirectionLeft;
import rznw.map.generator.direction.PathDirectionRight;
import rznw.map.generator.direction.PathDirectionUp;
import rznw.map.generator.path.MapPath;
import rznw.map.generator.path.MapPathCache;

import java.util.Vector;

public class ShortestPathCalculator
{
    public static MapPath calculateShortestPath(Map map, MapPoint finalPoint, MapPath[] paths, MapPathCache pathCache, boolean voidWalk)
    {
        if (paths.length == 0)
        {
            return null;
        }

        for (int i = 0; i < paths.length; i++)
        {
            MapPath path = paths[i];
            MapPoint currentPoint = path.getCurrentPoint();

            if (currentPoint.equals(finalPoint))
            {
                return path;
            }
        }

        Vector<MapPath> newPaths = new Vector<MapPath>();

        for (int i = 0; i < paths.length; i++)
        {
            pathCache.registerPathAsUsed(paths[i]);
        }

        for (int i = 0; i < paths.length; i++)
        {
            MapPath upPath = paths[i].getSubsequentPath(new PathDirectionUp());
            MapPoint upPathPoint = upPath.getCurrentPoint();
            if (upPathPoint.getY() >= 0 && !pathCache.pathIsUsed(upPath) && !(map.getElement(upPathPoint.getY(), upPathPoint.getX()) instanceof Wall) || upPath.getCurrentPoint().equals(finalPoint))
            {
                if (voidWalk || !(map.getElement(upPathPoint.getY(), upPathPoint.getX()) instanceof Void))
                {
                    newPaths.add(upPath);
                }
            }

            MapPath downPath = paths[i].getSubsequentPath(new PathDirectionDown());
            MapPoint downPathPoint = downPath.getCurrentPoint();
            if (downPathPoint.getY() < Map.NUM_ROWS && !pathCache.pathIsUsed(downPath) && !(map.getElement(downPathPoint.getY(), downPathPoint.getX()) instanceof Wall) || downPath.getCurrentPoint().equals(finalPoint))
            {
                if (voidWalk || !(map.getElement(downPathPoint.getY(), downPathPoint.getX()) instanceof Void))
                {
                    newPaths.add(downPath);
                }
            }

            MapPath leftPath = paths[i].getSubsequentPath(new PathDirectionLeft());
            MapPoint leftPathPoint = leftPath.getCurrentPoint();
            if (leftPathPoint.getX() >= 0 && !pathCache.pathIsUsed(leftPath) && !(map.getElement(leftPathPoint.getY(), leftPathPoint.getX()) instanceof Wall) || leftPath.getCurrentPoint().equals(finalPoint))
            {
                if (voidWalk || !(map.getElement(leftPathPoint.getY(), leftPathPoint.getX()) instanceof Void))
                {
                    newPaths.add(leftPath);
                }
            }

            MapPath rightPath = paths[i].getSubsequentPath(new PathDirectionRight());
            MapPoint rightPathPoint = rightPath.getCurrentPoint();
            if (rightPathPoint.getX() < Map.NUM_COLUMNS && !pathCache.pathIsUsed(rightPath) && !(map.getElement(rightPathPoint.getY(), rightPathPoint.getX()) instanceof Wall) || rightPath.getCurrentPoint().equals(finalPoint))
            {
                if (voidWalk || !(map.getElement(rightPathPoint.getY(), rightPathPoint.getX()) instanceof Void))
                {
                    newPaths.add(rightPath);
                }
            }
        }

        Vector<MapPath> uniquePaths = ShortestPathCalculator.makePathsUnique(newPaths);

        return ShortestPathCalculator.calculateShortestPath(map, finalPoint, uniquePaths.toArray(new MapPath[uniquePaths.size()]), pathCache, voidWalk);
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

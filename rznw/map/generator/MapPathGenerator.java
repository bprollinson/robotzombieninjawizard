package rznw.map.generator;

import rznw.map.Map;
import rznw.map.element.Path;
import rznw.map.element.Wall;
import rznw.map.generator.MapArea;
import rznw.map.generator.direction.PathDirection;
import rznw.map.generator.direction.PathDirectionDown;
import rznw.map.generator.direction.PathDirectionLeft;
import rznw.map.generator.direction.PathDirectionRight;
import rznw.map.generator.direction.PathDirectionUp;
import rznw.map.generator.direction.PathDirectionFactory;
import rznw.utility.RandomNumberGenerator;

import java.util.List;
import java.util.Vector;

public class MapPathGenerator
{
    private static final int RANDOM_PATH_PROBABILITY = 10;

    private MapAreaPointAggregator pointAggregator;
    private OpenAreaPadder padder;

    public MapPathGenerator()
    {
        this.pointAggregator = new MapAreaPointAggregator();
    }

    public void generatePaths(Map map, List<MapArea> rooms)
    {
        PathCollection pathCollection = new PathCollection(rooms);

        System.out.println("!!!Adding necessary paths!!!");
        while (pathCollection.partitionExists())
        {
            this.addShortestUnsetPath(map, rooms, pathCollection);
        }

        System.out.println();
        System.out.println("!!!Adding some additional unset paths at random!!!");
        this.addAllUnsetPathsWithProbability(map, rooms, pathCollection);
    }

    private void addShortestUnsetPath(Map map, List<MapArea> rooms, PathCollection pathCollection)
    {
        int shortestI = -1;
        int shortestJ = -1;
        double shortestDistance = 1000;

        for (int i = 0; i < rooms.size(); i++)
        {
            for (int j = 0; j < rooms.size(); j++)
            {
                if (!pathCollection.isIndirectlyAdjacent(i, j))
                {
                    double distance = rooms.get(i).getDistanceTo(rooms.get(j));

                    if (distance < shortestDistance)
                    {
                        shortestI = i;
                        shortestJ = j;
                        shortestDistance = distance;
                    }
                }
            }
        }

        this.addPath(map, rooms, pathCollection, shortestI, shortestJ);
    }

    private void addAllUnsetPathsWithProbability(Map map, List<MapArea> rooms, PathCollection pathCollection)
    {
        for (int i = 0; i < rooms.size(); i++)
        {
            for (int j = 0; j < rooms.size(); j++)
            {
                if (!pathCollection.isDirectlyAdjacent(i, j))
                {
                    int random = RandomNumberGenerator.randomInteger(1, 100);
                    if (random <= MapPathGenerator.RANDOM_PATH_PROBABILITY)
                    {
                        this.addPath(map, rooms, pathCollection, i, j);
                    }
                }
            }
        }
    }

    private void addPath(Map map, List<MapArea> rooms, PathCollection pathCollection, int i, int j)
    {
        MapArea room1 = rooms.get(i);
        MapArea room2 = rooms.get(j);

        System.out.println("Adding path: " + i + " " + j);
        System.out.println("Room 1 coordinates: (" + room1.getStartX() + "," + room1.getStartY() + ") to (" + room1.getEndX() + "," + room1.getEndY() + ")");
        System.out.println("Room 2 coordinates: (" + room2.getStartX() + "," + room2.getStartY() + ") to (" + room2.getEndX() + "," + room2.getEndY() + ")");

        pathCollection.setDirectlyAdjacent(i, j);

        PathDirection[] possibleDirectionOut = PathDirectionFactory.getFromRooms(room1, room2);

        System.out.println("Possible directions out");
        for (int k = 0; k < possibleDirectionOut.length; k++)
        {
            System.out.println(possibleDirectionOut[k]);
        }

        int outIndex = RandomNumberGenerator.randomInteger(0, possibleDirectionOut.length - 1);
        PathDirection directionOut = possibleDirectionOut[outIndex];
        System.out.println("Direction out: " + directionOut);

        MapPoint point1 = this.getRandomWallAdjacentPoint(room1, directionOut);

        PathDirection[] possibleDirectionIn = PathDirectionFactory.getOppositeDirections(possibleDirectionOut);

        System.out.println("Possible directions in");
        for (int k = 0; k < possibleDirectionIn.length; k++)
        {
            System.out.println(possibleDirectionIn[k]);
        }

        int inIndex = RandomNumberGenerator.randomInteger(0, possibleDirectionIn.length - 1);
        PathDirection directionIn = possibleDirectionIn[inIndex];
        System.out.println("Direction in: " + directionIn);

        MapPoint point2 = this.getRandomWallAdjacentPoint(room2, directionIn);
        System.out.println("Leaving from: " + point1.getX() + ", " + point1.getY());
        System.out.println("Re-entering at: " + point2.getX() + ", " + point2.getY());

        MapPath path = this.calculatePath(map, rooms, point1, point2);
        System.out.println("Path is: " + path);

        this.renderPathOnMap(map, path);
    }

    private MapPoint getRandomWallAdjacentPoint(MapArea room, PathDirection directionFromRoom)
    {
        MapPoint[] wallPoints = this.pointAggregator.getWallPoints(room, directionFromRoom);

        MapPoint[] possiblePoints = new MapPoint[wallPoints.length];

        for (int i = 0; i < wallPoints.length; i++)
        {
            MapPoint wallPoint = wallPoints[i];
            possiblePoints[i] = new MapPoint(wallPoint.getX() + directionFromRoom.getDeltaX(), wallPoint.getY() + directionFromRoom.getDeltaY());
        }

        int randomIndex = RandomNumberGenerator.randomInteger(0, possiblePoints.length - 1);
        return possiblePoints[randomIndex];
    }

    private MapPath calculatePath(Map map, List<MapArea> rooms, MapPoint point1, MapPoint point2)
    {
        Map paddedMap = new Map();

        for (int i = 0; i < rooms.size(); i++)
        {
            MapArea room = rooms.get(i);
            MapArea paddedRoom = this.padder.addBordersToOpenArea(room, 1);

            MapTerrainGenerator.renderRoom(paddedMap, paddedRoom.getStartX(), paddedRoom.getStartY(), paddedRoom.getEndX(), paddedRoom.getEndY());
        }

        MapPath result = new MapPath(point1);
        MapPathCache pathCache = new MapPathCache();

        return this.calculateShortestPath(paddedMap, point2, new MapPath[]{result}, pathCache);
    }

    private MapPath calculateShortestPath(Map map, MapPoint finalPoint, MapPath[] paths, MapPathCache pathCache)
    {
        if (paths.length == 0)
        {
            System.out.println("!Fail!");
            return null;
        }

        for (int i = 0; i < paths.length; i++)
        {
            MapPath path = paths[i];
            MapPoint currentPoint = path.getCurrentPoint();

            if (currentPoint.equals(finalPoint))
            {
                System.out.println("Found the matching path!");
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
            if (upPathPoint.getY() >= 0 && !pathCache.pathIsUsed(upPath) && !(map.getElement(upPathPoint.getY(), upPathPoint.getX()) instanceof Wall))
            {
                newPaths.add(upPath);
            } else {
                if (upPath.getCurrentPoint().equals(finalPoint))
                {
                    newPaths.add(upPath);
                }
            }

            MapPath downPath = paths[i].getSubsequentPath(new PathDirectionDown());
            MapPoint downPathPoint = downPath.getCurrentPoint();
            if (downPathPoint.getY() < Map.NUM_ROWS && !pathCache.pathIsUsed(downPath) && !(map.getElement(downPathPoint.getY(), downPathPoint.getX()) instanceof Wall))
            {
                newPaths.add(downPath);
            } else {
                if (downPath.getCurrentPoint().equals(finalPoint))
                {
                    newPaths.add(downPath);
                }
            }

            MapPath leftPath = paths[i].getSubsequentPath(new PathDirectionLeft());
            MapPoint leftPathPoint = leftPath.getCurrentPoint();
            if (leftPathPoint.getX() >= 0 && !pathCache.pathIsUsed(leftPath) && !(map.getElement(leftPathPoint.getY(), leftPathPoint.getX()) instanceof Wall))
            {
                newPaths.add(leftPath);
            } else {
                if (leftPath.getCurrentPoint().equals(finalPoint))
                {
                    newPaths.add(leftPath);
                }
            }

            MapPath rightPath = paths[i].getSubsequentPath(new PathDirectionRight());
            MapPoint rightPathPoint = rightPath.getCurrentPoint();
            if (rightPathPoint.getX() < Map.NUM_COLUMNS && !pathCache.pathIsUsed(rightPath) && !(map.getElement(rightPathPoint.getY(), rightPathPoint.getX()) instanceof Wall))
            {
                newPaths.add(rightPath);
            } else {
                if (rightPath.getCurrentPoint().equals(finalPoint))
                {
                    newPaths.add(rightPath);
                }
            }
        }

        return this.calculateShortestPath(map, finalPoint, newPaths.toArray(new MapPath[newPaths.size()]), pathCache);
    }

    private void renderPathOnMap(Map map, MapPath path)
    {
        MapPoint[] points = path.getPoints();

        for (int i = 0; i < points.length; i++)
        {
            MapPoint point = points[i];
            map.setElement(point.getY(), point.getX(), new Path(point.getY(), point.getX()));
        }
    }
}

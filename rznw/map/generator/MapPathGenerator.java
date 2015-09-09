package rznw.map.generator;

import rznw.map.Map;
import rznw.map.generator.MapArea;
import rznw.utility.RandomNumberGenerator;

import java.util.List;

public class MapPathGenerator
{
    private static final int RANDOM_PATH_PROBABILITY = 10;

    public void generatePaths(Map map, List<MapArea> rooms)
    {
        PathCollection pathCollection = new PathCollection(rooms);

        System.out.println("Adding necessary paths");
        while (pathCollection.partitionExists())
        {
            this.addShortestUnsetPath(map, rooms, pathCollection);
        }

        System.out.println("Adding some additional unset paths at random");
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
                if (pathCollection.isDirectlyAdjacent(i, j))
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
        System.out.println("Adding path: " + i + " " + j);
        pathCollection.setDirectlyAdjacent(i, j);
    }
}

package rznw.map.generator;

import rznw.map.Map;
import rznw.map.generator.MapArea;

import java.util.List;

public class MapPathGenerator
{
    private static final int RANDOM_PATH_PROBABILITY = 10;

    public void generatePaths(Map map, List<MapArea> rooms)
    {
        AdjacencyList directPaths = new AdjacencyList(rooms);
        AdjacencyList allPaths = new AdjacencyList(rooms);

        while (allPaths.partitionExists())
        {
            this.addShortestUnsetPath(map, rooms, directPaths, allPaths);
        }

        //this.addAllUnsetPathsWithProbability(directPaths, allPaths, MapPathGenerator.RANDOM_PATH_PROBABILITY);
    }

    private void addShortestUnsetPath(Map map, List<MapArea> rooms, AdjacencyList directPaths, AdjacencyList allPaths)
    {
        int shortestI = -1;
        int shortestJ = -1;
        double shortestDistance = 1000;

        for (int i = 0; i < rooms.size(); i++)
        {
            for (int j = 0; j < rooms.size(); j++)
            {
                if (!allPaths.isAdjacent(i, j))
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

        System.out.println(shortestDistance);
        this.addPath(map, rooms, directPaths, allPaths, shortestI, shortestJ);
    }

    private void addPath(Map map, List<MapArea> rooms, AdjacencyList directPaths, AdjacencyList allPaths, int i, int j)
    {
        System.out.println("Adding path: " + i + " " + j);
        directPaths.setAdjacent(i, j);

        allPaths.setAdjacent(i, j);

        int[] adjacent = directPaths.getAdjacent(i);
        for (int k = 0; k < adjacent.length; k++)
        {
            allPaths.setAdjacent(j, adjacent[k]);
        }

        adjacent = directPaths.getAdjacent(j);
        for (int k = 0; k < adjacent.length; k++)
        {
            allPaths.setAdjacent(i, adjacent[k]);
        }
    }
}

package rznw.map.generator;

import rznw.map.Map;
import rznw.map.generator.area.MapArea;
import rznw.map.generator.path.PathCollection;
import rznw.utility.RandomNumberGenerator;

import java.util.List;

public class MapPathGenerator
{
    private static final int RANDOM_PATH_PROBABILITY = 10;

    private MapPathAdder pathAdder;

    public MapPathGenerator()
    {
        this.pathAdder = new MapPathAdder();
    }

    public void generatePaths(Map map, List<MapArea> rooms)
    {
        PathCollection pathCollection = new PathCollection(rooms);

        while (pathCollection.partitionExists())
        {
            this.addShortestUnsetPath(map, rooms, pathCollection);
        }

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

        this.pathAdder.addPath(map, rooms, pathCollection, shortestI, shortestJ);
    }

    private void addAllUnsetPathsWithProbability(Map map, List<MapArea> rooms, PathCollection pathCollection)
    {
        for (int i = 0; i < rooms.size(); i++)
        {
            for (int j = 0; j < rooms.size(); j++)
            {
                if (!pathCollection.isDirectlyAdjacent(i, j))
                {
                    if (RandomNumberGenerator.rollSucceeds(MapPathGenerator.RANDOM_PATH_PROBABILITY))
                    {
                        this.pathAdder.addPath(map, rooms, pathCollection, i, j);
                    }
                }
            }
        }
    }
}

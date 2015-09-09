package rznw.map.generator;

import java.util.List;

public class PathCollection
{
    private List<MapArea> rooms;
    private AdjacencyList directPaths;
    private AdjacencyList allPaths;

    public PathCollection(List<MapArea> rooms)
    {
        this.rooms = rooms;
        this.directPaths = new AdjacencyList(rooms);
        this.allPaths = new AdjacencyList(rooms);
    }

    public boolean partitionExists()
    {
        for (int i = 0; i < this.rooms.size(); i++)
        {
            for (int j = 0; j < this.rooms.size(); j++)
            {
                if (!this.allPaths.isAdjacent(i, j))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isDirectlyAdjacent(int i, int j)
    {
        return this.directPaths.isAdjacent(i, j);
    }

    public boolean isIndirectlyAdjacent(int i, int j)
    {
        return this.allPaths.isAdjacent(i, j);
    }

    public void setDirectlyAdjacent(int i, int j)
    {
        this.directPaths.setAdjacent(i, j);

        int[] adjacent1 = this.getAllIndirectlyAdjacentPaths(i);
        int[] adjacent2 = this.getAllIndirectlyAdjacentPaths(j);

        for (int k = 0; k < adjacent1.length; k++)
        {
            for (int l = 0; l < adjacent2.length; l++)
            {
                this.allPaths.setAdjacent(adjacent1[k], adjacent2[l]);
            }
        }
    }

    private int[] getAllIndirectlyAdjacentPaths(int i)
    {
        int numAdjacent = 0;
        for (int j = 0; j < this.rooms.size(); j++)
        {
            if (this.allPaths.isAdjacent(i, j))
            {
                numAdjacent++;
            }
        }

        int[] result = new int[numAdjacent];

        int pos = 0;
        for (int j = 0; j < this.rooms.size(); j++)
        {
            if (this.allPaths.isAdjacent(i, j))
            {
                result[pos] = j;
                pos++;
            }
        }

        return result;
    }
}

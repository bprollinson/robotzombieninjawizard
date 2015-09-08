package rznw.map.generator;

import java.util.HashMap;
import java.util.List;

public class AdjacencyList
{
    private List<MapArea> rooms;
    private HashMap<MapArea, Integer> reverseLookup;
    private Boolean[][] matrix;

    public AdjacencyList(List<MapArea> rooms)
    {
        this.rooms = rooms;
        this.initializeMatrix(this.rooms);
    }

    public boolean partitionExists()
    {
        for (int i = 0; i < this.rooms.size(); i++)
        {
            for (int j = 0; j < this.rooms.size(); j++)
            {
                if (this.matrix[i][j].equals(Boolean.FALSE))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isAdjacent(int i, int j)
    {
        return this.matrix[i][j].equals(Boolean.TRUE);
    }

    public void setAdjacent(int i, int j)
    {
        this.matrix[i][j] = Boolean.TRUE;
        this.matrix[j][i] = Boolean.TRUE;
    }

    public int[] getAdjacent(int i)
    {
        int numAdjacent = 0;
        for (int j = 0; j < this.rooms.size(); j++)
        {
            if (this.isAdjacent(i, j))
            {
                numAdjacent++;
            }
        }

        int[] result = new int[numAdjacent];

        int pos = 0;
        for (int j = 0; j < this.rooms.size(); j++)
        {
            if (this.isAdjacent(i, j))
            {
                result[pos] = j;
                pos++;
            }
        }

        return result;
    }

    private void initializeMatrix(List<MapArea> rooms)
    {
        this.reverseLookup = new HashMap<MapArea, Integer>();
        for (int i = 0; i < rooms.size(); i++)
        {
            reverseLookup.put(rooms.get(i), new Integer(i));
        }

        this.matrix = new Boolean[rooms.size()][rooms.size()];
        for (int i = 0; i < rooms.size(); i++)
        {
            for (int j = 0; j < rooms.size(); j++)
            {
                this.matrix[i][j] = i == j ? Boolean.TRUE : Boolean.FALSE;
            }
        }
    }
}

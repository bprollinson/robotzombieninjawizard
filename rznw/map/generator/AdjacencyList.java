package rznw.map.generator;

import java.util.HashMap;
import java.util.List;

public class AdjacencyList
{
    private List<MapArea> rooms;
    private Boolean[][] matrix;

    public AdjacencyList(List<MapArea> rooms)
    {
        this.rooms = rooms;
        this.initializeMatrix(this.rooms);
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

    private void initializeMatrix(List<MapArea> rooms)
    {
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

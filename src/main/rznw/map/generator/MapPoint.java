package rznw.map.generator;

public class MapPoint
{
    private int row;
    private int column;

    public MapPoint(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    public int getRow()
    {
        return this.row;
    }

    public int getColumn()
    {
        return this.column;
    }

    public MapPoint clone()
    {
        return new MapPoint(this.row, this.column);
    }

    public boolean equals(MapPoint otherPoint)
    {
        return this.row == otherPoint.getRow() && this.column == otherPoint.getColumn();
    }

    public String toString()
    {
        return "(" + this.row + "," + this.column + ")";
    }
}

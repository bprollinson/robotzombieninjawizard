package rznw.map.generator;

public class MapPoint
{
    private int x;
    private int y;

    public MapPoint(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public MapPoint clone()
    {
        return new MapPoint(this.x, this.y);
    }

    public boolean equals(MapPoint otherPoint)
    {
        return this.x == otherPoint.getX() && this.y == otherPoint.getY();
    }

    public String toString()
    {
        return "(" + this.x + "," + this.y + ")";
    }
}

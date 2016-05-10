package rznw.map.generator.direction;

public class PathDirectionDownLeft extends PathDirection
{
    public PathDirectionDownLeft()
    {
        super(1, -1);
    }

    public String toString()
    {
        return "3";
    }

    public PathDirection getOppositeDirection()
    {
        return new PathDirectionUpRight();
    }
}

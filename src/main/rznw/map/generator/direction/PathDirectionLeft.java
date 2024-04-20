package rznw.map.generator.direction;

public class PathDirectionLeft extends PathDirection
{
    public PathDirectionLeft()
    {
        super(0, -1);
    }

    public String toString()
    {
        return "L";
    }

    public PathDirection getOppositeDirection()
    {
        return new PathDirectionRight();
    }
}

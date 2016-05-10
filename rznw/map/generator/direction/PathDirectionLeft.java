package rznw.map.generator.direction;

public class PathDirectionLeft extends PathDirection
{
    public PathDirectionLeft()
    {
        super(-1, 0);
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

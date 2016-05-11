package rznw.map.generator.direction;

public class PathDirectionRight extends PathDirection
{
    public PathDirectionRight()
    {
        super(0, 1);
    }

    public String toString()
    {
        return "R";
    }

    public PathDirection getOppositeDirection()
    {
        return new PathDirectionLeft();
    }
}

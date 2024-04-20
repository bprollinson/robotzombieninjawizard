package rznw.map.generator.direction;

public class PathDirectionUpLeft extends PathDirection
{
    public PathDirectionUpLeft()
    {
        super(-1, -1);
    }

    public String toString()
    {
        return "1";
    }

    public PathDirection getOppositeDirection()
    {
        return new PathDirectionDownRight();
    }
}

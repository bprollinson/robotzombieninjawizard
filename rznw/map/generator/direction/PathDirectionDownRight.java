package rznw.map.generator.direction;

public class PathDirectionDownRight extends PathDirection
{
    public PathDirectionDownRight()
    {
        super(1, 1);
    }

    public String toString()
    {
        return "4";
    }

    public PathDirection getOppositeDirection()
    {
        return new PathDirectionUpLeft();
    }
}

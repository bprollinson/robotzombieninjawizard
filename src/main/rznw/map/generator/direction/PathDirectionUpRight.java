package rznw.map.generator.direction;

public class PathDirectionUpRight extends PathDirection
{
    public PathDirectionUpRight()
    {
        super(1, -1);
    }

    public String toString()
    {
        return "2";
    }

    public PathDirection getOppositeDirection()
    {
        return new PathDirectionDownLeft();
    }
}

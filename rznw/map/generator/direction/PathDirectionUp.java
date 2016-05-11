package rznw.map.generator.direction;

public class PathDirectionUp extends PathDirection
{
    public PathDirectionUp()
    {
        super(-1, 0);
    }

    public String toString()
    {
        return "U";
    }

    public PathDirection getOppositeDirection()
    {
        return new PathDirectionDown();
    }
}

package rznw.map.generator.direction;

public class PathDirectionDown extends PathDirection
{
    public PathDirectionDown()
    {
        super(1, 0);
    }

    public String toString()
    {
        return "D";
    }

    public PathDirection getOppositeDirection()
    {
        return new PathDirectionUp();
    }
}

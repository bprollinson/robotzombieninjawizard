package rznw.map.generator.direction;

public abstract class PathDirection
{
    protected int deltaX;
    protected int deltaY;

    public PathDirection(int deltaX, int deltaY)
    {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX()
    {
        return this.deltaX;
    }

    public int getDeltaY()
    {
        return this.deltaY;
    }
}

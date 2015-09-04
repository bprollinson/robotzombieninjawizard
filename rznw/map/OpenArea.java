package rznw.map;

public class OpenArea
{
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public OpenArea(int startX, int startY, int endX, int endY)
    {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public int getStartX()
    {
        return this.startX;
    }

    public int getStartY()
    {
        return this.startY;
    }

    public int getEndX()
    {
        return this.endX;
    }

    public int getEndY()
    {
        return this.endY;
    }

    public int getWidth()
    {
        return this.endX - this.startX + 1;
    }

    public int getHeight()
    {
        return this.endY - this.startY + 1;
    }
}

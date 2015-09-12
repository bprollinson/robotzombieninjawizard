package rznw.map.generator.area;

public class MapArea
{
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public MapArea(int startX, int startY, int endX, int endY)
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

    public int getMaxStartXForRectangle(int width)
    {
        return this.endX - width + 1;
    }

    public int getMaxStartYForRectangle(int height)
    {
        return this.endY - height + 1;
    }

    public int getSmallestDimensionSize()
    {
        int width = this.getWidth();
        int height = this.getHeight();

        return Math.min(width, height);
    }

    public boolean fallsWithin(MapArea otherArea)
    {
        return this.startX > otherArea.getStartX() && this.endX < otherArea.getEndX() && this.startY > otherArea.getStartY() && this.endY < otherArea.getEndY();
    }

    public double getDistanceTo(MapArea otherArea)
    {
        double centerX = this.getCenterX();
        double centerY = this.getCenterY();
        double otherCenterX = otherArea.getCenterX();
        double otherCenterY = otherArea.getCenterY();

        return Math.sqrt(Math.pow((otherCenterX - centerX), 2) + Math.pow((otherCenterY - centerY), 2));
    }

    public double getCenterX()
    {
        return (this.startX + this.endX + 1) / 2;
    }

    public double getCenterY()
    {
        return (this.startY + this.endY + 1) / 2;
    }
}

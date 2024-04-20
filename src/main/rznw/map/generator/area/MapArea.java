package rznw.map.generator.area;

public class MapArea
{
    private int startRow;
    private int startColumn;
    private int endRow;
    private int endColumn;

    public MapArea(int startRow, int startColumn, int endRow, int endColumn)
    {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
    }

    public int getStartRow()
    {
        return this.startRow;
    }

    public int getStartColumn()
    {
        return this.startColumn;
    }

    public int getEndRow()
    {
        return this.endRow;
    }

    public int getEndColumn()
    {
        return this.endColumn;
    }

    public int getWidth()
    {
        return this.endColumn - this.startColumn + 1;
    }

    public int getHeight()
    {
        return this.endRow - this.startRow + 1;
    }

    public int getMaxStartRowForRectangle(int height)
    {
        return this.endRow - height + 1;
    }

    public int getMaxStartColumnForRectangle(int width)
    {
        return this.endColumn - width + 1;
    }

    public int getSmallestDimensionSize()
    {
        int height = this.getHeight();
        int width = this.getWidth();

        return Math.min(height, width);
    }

    public boolean fallsWithin(MapArea otherArea)
    {
        return this.startColumn > otherArea.getStartColumn() && this.endColumn < otherArea.getEndColumn() && this.startRow > otherArea.getStartRow() && this.endRow < otherArea.getEndRow();
    }

    public double getDistanceTo(MapArea otherArea)
    {
        double centerRow = this.getCenterRow();
        double centerColumn = this.getCenterColumn();
        double otherCenterRow = otherArea.getCenterRow();
        double otherCenterColumn = otherArea.getCenterColumn();

        return Math.sqrt(Math.pow((otherCenterRow - centerRow), 2) + Math.pow((otherCenterColumn - centerColumn), 2));
    }

    private double getCenterRow()
    {
        return (this.startRow + this.endRow + 1) / 2;
    }

    private double getCenterColumn()
    {
        return (this.startColumn + this.endColumn + 1) / 2;
    }
}

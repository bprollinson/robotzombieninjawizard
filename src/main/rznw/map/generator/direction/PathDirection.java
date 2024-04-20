package rznw.map.generator.direction;

public abstract class PathDirection
{
    protected int deltaRow;
    protected int deltaColumn;

    public PathDirection(int deltaRow, int deltaColumn)
    {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    public int getDeltaRow()
    {
        return this.deltaRow;
    }

    public int getDeltaColumn()
    {
        return this.deltaColumn;
    }

    public abstract PathDirection getOppositeDirection();
}

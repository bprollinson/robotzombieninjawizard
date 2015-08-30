package rznw.turn.positionchange;

public class PositionChange
{
    protected int initialRow;
    protected int initialColumn;

    protected int deltaRow;
    protected int deltaColumn;

    protected int finalRow;
    protected int finalColumn;

    public boolean isChange()
    {
        return this.deltaRow != 0 || this.deltaColumn != 0;
    }

    public int getInitialRow()
    {
        return this.initialRow;
    }

    public int getInitialColumn()
    {
        return this.initialColumn;
    }

    public int getFinalRow()
    {
        return this.finalRow;
    }

    public int getFinalColumn()
    {
        return this.finalColumn;
    }
}

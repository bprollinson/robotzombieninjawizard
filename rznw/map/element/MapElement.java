package rznw.map.element;

public abstract class MapElement
{
    private int row;
    private int column;

    public abstract char getDisplayCharacter();

    public MapElement(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    public int getRow()
    {
        return this.row;
    }

    public int getColumn()
    {
        return this.column;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public void setColumn(int column)
    {
        this.column = column;
    }
}

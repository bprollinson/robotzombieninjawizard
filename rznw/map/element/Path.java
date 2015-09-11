package rznw.map.element;

public class Path extends MapElement
{
    public Path(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return '*';
    }
}

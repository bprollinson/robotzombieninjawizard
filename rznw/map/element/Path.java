package rznw.map.element;

public class Path extends MapElement
{
    private static final int ELEMENT_NUMBER = 6;

    public Path(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return '*';
    }

    public int getElementNumber()
    {
        return Path.ELEMENT_NUMBER;
    }
}

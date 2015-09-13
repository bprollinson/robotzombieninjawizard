package rznw.map.element;

public class Stairs extends MapElement
{
    public Stairs(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return 'V';
    }
}

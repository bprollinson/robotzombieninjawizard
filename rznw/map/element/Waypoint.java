package rznw.map.element;

public class Waypoint extends MapElement
{
    public Waypoint(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return '&';
    }
}

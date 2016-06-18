package rznw.map.element;

public class Waypoint extends MapElement
{
    public static final int ELEMENT_NUMBER = 15;

    public Waypoint(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return '&';
    }

    public int getElementNumber()
    {
        return Waypoint.ELEMENT_NUMBER;
    }
}

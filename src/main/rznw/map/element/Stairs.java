package rznw.map.element;

public class Stairs extends MapElement
{
    public static final int ELEMENT_NUMBER = 9;

    public Stairs(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return 'V';
    }

    public int getElementNumber()
    {
        return Stairs.ELEMENT_NUMBER;
    }
}

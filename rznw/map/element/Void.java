package rznw.map.element;

public class Void extends MapElement
{
    public static final int ELEMENT_NUMBER = 13;

    public Void(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return ' ';
    }

    public int getElementNumber()
    {
        return Void.ELEMENT_NUMBER;
    }
}

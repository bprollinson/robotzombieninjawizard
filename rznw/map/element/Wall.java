package rznw.map.element;

public class Wall extends MapElement
{
    private static final int ELEMENT_NUMBER = 14;

    public Wall(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return '#';
    }

    public int getElementNumber()
    {
        return Wall.ELEMENT_NUMBER;
    }
}

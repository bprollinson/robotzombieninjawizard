package rznw.map.element;

public class Void extends MapElement
{
    public Void(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return ' ';
    }
}

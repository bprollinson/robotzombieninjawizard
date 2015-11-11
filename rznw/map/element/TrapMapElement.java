package rznw.map.element;

public class TrapMapElement extends MapElement
{
    public TrapMapElement(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return '-';
    }
}

package rznw.map.element;

public class Blotch extends MapElement
{
    public Blotch(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return ';';
    }
}

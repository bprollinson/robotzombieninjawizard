package rznw.map.element;

public class Wall extends MapElement
{
    public Wall(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return '#';
    }
}

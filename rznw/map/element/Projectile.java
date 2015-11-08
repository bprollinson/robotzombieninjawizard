package rznw.map.element;

public class Projectile extends MapElement
{
    public Projectile(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return '+';
    }
}

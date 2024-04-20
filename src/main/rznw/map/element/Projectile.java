package rznw.map.element;

public class Projectile extends MapElement
{
    public static final int ELEMENT_NUMBER = 7;

    public Projectile(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return '+';
    }

    public int getElementNumber()
    {
        return Projectile.ELEMENT_NUMBER;
    }
}

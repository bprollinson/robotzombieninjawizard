package rznw.map.element;

public class EnemyMapElement extends MapElement
{
    private char displayCharacter;

    public EnemyMapElement(int row, int column, char displayCharacter)
    {
        super(row, column);

        this.displayCharacter = displayCharacter;
    }

    public char getDisplayCharacter()
    {
        return this.displayCharacter;
    }
}

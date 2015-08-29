package rznw.map.element;

public class CharacterMapElement extends MapElement
{
    private char displayCharacter;

    public CharacterMapElement(int row, int column, char displayCharacter)
    {
        super(row, column);

        this.displayCharacter = displayCharacter;
    }

    public char getDisplayCharacter()
    {
        return this.displayCharacter;
    }
}

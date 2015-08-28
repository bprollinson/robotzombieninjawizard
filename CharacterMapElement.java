public class CharacterMapElement extends MapElement
{
    public CharacterMapElement(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return '@';
    }
}

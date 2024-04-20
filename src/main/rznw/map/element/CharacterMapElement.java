package rznw.map.element;

import rznw.game.Character;

public abstract class CharacterMapElement extends MapElement
{
    private char displayCharacter;
    protected Character character;

    public CharacterMapElement(int row, int column, char displayCharacter, Character character)
    {
        super(row, column);

        this.displayCharacter = displayCharacter;
        this.character = character;
    }

    public char getDisplayCharacter()
    {
        return this.displayCharacter;
    }

    public Character getCharacter()
    {
        return this.character;
    }

    public void setCharacter(Character character)
    {
        this.character = character;
    }
}

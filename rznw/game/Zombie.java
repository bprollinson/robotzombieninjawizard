package rznw.game;

import rznw.map.element.CharacterMapElement;

public class Zombie extends GameCharacter
{
    private static char mapCharacter = 'Z';

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new CharacterMapElement(row, column, Zombie.mapCharacter);
    }
}

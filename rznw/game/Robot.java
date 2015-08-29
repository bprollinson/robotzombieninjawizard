package rznw.game;

import rznw.map.element.CharacterMapElement;

public class Robot extends GameCharacter
{
    private static char mapCharacter = 'R';

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new CharacterMapElement(row, column, Robot.mapCharacter);
    }
}

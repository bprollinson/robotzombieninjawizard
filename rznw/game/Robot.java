package rznw.game;

import rznw.map.element.CharacterMapElement;

public class Robot extends MainCharacter
{
    private static char mapCharacter = 'R';

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new CharacterMapElement(row, column, Robot.mapCharacter);
    }
}

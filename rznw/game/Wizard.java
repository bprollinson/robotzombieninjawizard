package rznw.game;

import rznw.map.element.CharacterMapElement;

public class Wizard extends GameCharacter
{
    private static char mapCharacter = 'W';

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new CharacterMapElement(row, column, Wizard.mapCharacter);
    }
}

package rznw.game.maincharacter;

import rznw.map.element.CharacterMapElement;

public class Wizard extends MainCharacter
{
    private static char mapCharacter = 'W';

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new CharacterMapElement(row, column, Wizard.mapCharacter);
    }
}
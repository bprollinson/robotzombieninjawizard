package rznw.game.maincharacter;

import rznw.map.element.CharacterMapElement;

public class Ninja extends MainCharacter
{
    private static char mapCharacter = 'N';

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new CharacterMapElement(row, column, Ninja.mapCharacter);
    }
}

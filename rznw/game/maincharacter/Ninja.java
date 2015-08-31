package rznw.game.maincharacter;

import rznw.map.element.MainCharacterMapElement;

public class Ninja extends MainCharacter
{
    private static char mapCharacter = 'N';

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new MainCharacterMapElement(row, column, Ninja.mapCharacter, this);
    }
}

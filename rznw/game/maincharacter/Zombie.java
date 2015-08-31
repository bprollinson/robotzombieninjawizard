package rznw.game.maincharacter;

import rznw.map.element.MainCharacterMapElement;

public class Zombie extends MainCharacter
{
    private static char mapCharacter = 'Z';

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new MainCharacterMapElement(row, column, Zombie.mapCharacter, this);
    }
}

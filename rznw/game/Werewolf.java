package rznw.game;

import rznw.map.element.EnemyMapElement;

public class Werewolf extends EnemyCharacter
{
    private static char mapCharacter = 'w';

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Werewolf.mapCharacter);
    }
}

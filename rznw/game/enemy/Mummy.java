package rznw.game.enemy;

import rznw.map.element.EnemyMapElement;

public class Mummy extends EnemyCharacter
{
    private static char mapCharacter = 'm';

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new EnemyMapElement(row, column, Mummy.mapCharacter);
    }
}

package rznw.map.element;

import rznw.game.enemy.EnemyCharacter;

public class EnemyMapElement extends CharacterMapElement
{
    public EnemyMapElement(int row, int column, char displayCharacter, EnemyCharacter enemyCharacter)
    {
        super(row, column, displayCharacter, enemyCharacter);
    }

    public boolean isEnemy()
    {
        return true;
    }
}

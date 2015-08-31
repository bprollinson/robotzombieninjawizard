package rznw.map.element;

import rznw.game.enemy.EnemyCharacter;

public class EnemyMapElement extends CharacterMapElement
{
    private EnemyCharacter enemyCharacter;

    public EnemyMapElement(int row, int column, char displayCharacter, EnemyCharacter enemyCharacter)
    {
        super(row, column, displayCharacter);

        this.enemyCharacter = enemyCharacter;
    }

    public EnemyCharacter getEnemyCharacter()
    {
        return this.enemyCharacter;
    }
}

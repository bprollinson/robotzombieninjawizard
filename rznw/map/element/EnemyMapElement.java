package rznw.map.element;

import rznw.game.enemy.EnemyCharacter;

public class EnemyMapElement extends MapElement
{
    private char displayCharacter;
    private EnemyCharacter enemyCharacter;

    public EnemyMapElement(int row, int column, char displayCharacter, EnemyCharacter enemyCharacter)
    {
        super(row, column);

        this.displayCharacter = displayCharacter;
        this.enemyCharacter = enemyCharacter;
    }

    public char getDisplayCharacter()
    {
        return this.displayCharacter;
    }

    public EnemyCharacter getEnemyCharacter()
    {
        return this.enemyCharacter;
    }
}

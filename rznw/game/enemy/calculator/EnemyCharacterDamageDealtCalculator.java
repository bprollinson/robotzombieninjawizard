package rznw.game.enemy.calculator;

import rznw.game.enemy.EnemyCharacter;

public class EnemyCharacterDamageDealtCalculator
{
    public int getDamage(EnemyCharacter enemyCharacter)
    {
        System.out.println("Enemy damage: " + (10 + 2 * enemyCharacter.getStatPoints(EnemyCharacter.STAT_DAMAGE)));
        return 10 + 2 * enemyCharacter.getStatPoints(EnemyCharacter.STAT_DAMAGE);
    }
}

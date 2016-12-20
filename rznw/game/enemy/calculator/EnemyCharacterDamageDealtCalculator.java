package rznw.game.enemy.calculator;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyStat;

public class EnemyCharacterDamageDealtCalculator
{
    public int getDamage(EnemyCharacter enemyCharacter)
    {
        return 10 + 2 * enemyCharacter.getStatPoints(EnemyStat.STAT_DAMAGE);
    }
}

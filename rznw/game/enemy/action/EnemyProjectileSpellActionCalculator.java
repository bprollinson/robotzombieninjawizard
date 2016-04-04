package rznw.game.enemy.action;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;

public class EnemyProjectileSpellActionCalculator extends EnemyActionCalculator
{
    public EnemyAIBasedPositionChange getPositionChange(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        EnemyAIBasedPositionChange confusionPositionChange = this.getConfusionPositionChange(gameWorld, enemyCharacter);
        if (confusionPositionChange != null)
        {
            return confusionPositionChange;
        }

        return this.getPathBasedPositionChange(gameWorld, enemyCharacter);
    }
}

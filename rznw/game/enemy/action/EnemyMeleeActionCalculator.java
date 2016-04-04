package rznw.game.enemy.action;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;

public class EnemyMeleeActionCalculator extends EnemyActionCalculator
{
    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        EnemyAction confusionPositionChange = this.getConfusionPositionChange(gameWorld, enemyCharacter);
        if (confusionPositionChange != null)
        {
            return confusionPositionChange;
        }

        return this.getPathBasedPositionChange(gameWorld, enemyCharacter);
    }
}

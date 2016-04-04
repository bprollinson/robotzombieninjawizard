package rznw.game.enemy.action;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;
import rznw.utility.RandomNumberGenerator;

public abstract class EnemyActionCalculator
{
    public abstract EnemyAIBasedPositionChange getPositionChange(GameWorld gameWorld, EnemyCharacter enemyCharacter);

    protected EnemyAIBasedPositionChange getConfusionPositionChange(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        if (enemyCharacter.getStatusEffects().isConfused() || enemyCharacter.distanceFromMainCharacter(gameWorld) > enemyCharacter.getViewRadius())
        {
            if (enemyCharacter.getStatusEffects().isConfused())
            {
                System.out.println("Enemy is confused!");
            }

            return this.getRandomPositionChange(enemyCharacter);
        }

        return null;
    }

    private EnemyAIBasedPositionChange getRandomPositionChange(EnemyCharacter enemyCharacter)
    {
        EnemyAIBasedPositionChange possibleChanges[] = {
           new EnemyAIBasedPositionChange(enemyCharacter, -1, -1),
           new EnemyAIBasedPositionChange(enemyCharacter, -1, 0),
           new EnemyAIBasedPositionChange(enemyCharacter, -1, 1),
           new EnemyAIBasedPositionChange(enemyCharacter, 0, -1),
           new EnemyAIBasedPositionChange(enemyCharacter, 0, 1),
           new EnemyAIBasedPositionChange(enemyCharacter, 1, -1),
           new EnemyAIBasedPositionChange(enemyCharacter, 1, 0),
           new EnemyAIBasedPositionChange(enemyCharacter, 1, 1)
        };

        int randomPosition = RandomNumberGenerator.randomInteger(0, possibleChanges.length - 1);
        return possibleChanges[randomPosition];
    }
}

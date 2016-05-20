package rznw.game.enemy.action.choice;

import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.action.EnemyMovementAction;
import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;
import rznw.turn.positionchange.RandomPositionChange;

public class ConfusionChoice extends EnemyActionChoice
{
    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        if (enemyCharacter.getStatusEffects().isConfused() || enemyCharacter.distanceFromMainCharacter(gameWorld) > enemyCharacter.getViewRadius())
        {
            if (enemyCharacter.getStatusEffects().isConfused())
            {
                System.out.println("Enemy is confused!");
            }

            return new EnemyMovementAction(new RandomPositionChange(enemyCharacter));
        }

        return null;
    }
}

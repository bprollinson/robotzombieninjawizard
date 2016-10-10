package rznw.game.enemy.action.choice;

import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.action.EnemyMovementAction;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.CharacterDistanceCalculator;
import rznw.map.GameWorld;
import rznw.turn.positionchange.RandomPositionChange;

public class ConfusionChoice extends EnemyActionChoice
{
    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        CharacterDistanceCalculator distanceCalculator = new CharacterDistanceCalculator();
        double distance = distanceCalculator.calculateDistance(gameWorld.getMainCharacter(), enemyCharacter);

        if (enemyCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_CONFUSION) > 0 || distance > enemyCharacter.getViewRadius())
        {
            if (enemyCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_CONFUSION) > 0)
            {
                System.out.println("Enemy is confused!");
            }

            return new EnemyMovementAction(new RandomPositionChange(enemyCharacter));
        }

        return null;
    }
}

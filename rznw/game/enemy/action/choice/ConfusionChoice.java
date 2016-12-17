package rznw.game.enemy.action.choice;

import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.action.EnemyMovementAction;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.CharacterDistanceCalculator;
import rznw.map.GameWorld;
import rznw.turn.positionchange.RandomPositionChange;
import rznw.ui.LogRendererFactory;
import rznw.utility.StringUtils;

public class ConfusionChoice extends EnemyActionChoice
{
    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        CharacterDistanceCalculator distanceCalculator = new CharacterDistanceCalculator();
        double distance = distanceCalculator.calculateDistance(gameWorld.getMainCharacter(), enemyCharacter);

        if (enemyCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_CONFUSION) > 0)
        {
            StringUtils utils = new StringUtils();
            LogRendererFactory.instance().log(utils.UCFirst(enemyCharacter.getLogName()) + " is confused.");
        }

        if (enemyCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_CONFUSION) > 0 || distance > enemyCharacter.getViewRadius())
        {
            return new EnemyMovementAction(new RandomPositionChange(enemyCharacter));
        }

        return null;
    }
}

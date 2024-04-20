package rznw.game.enemy.action.choice;

import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.action.EnemyMovementAction;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.SummonedMinionMapElement;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;

public class SummonAttackChoice extends EnemyActionChoice
{
    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        boolean isConfused = enemyCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_CONFUSION) > 0;

        MapElement mainCharacterElement = gameWorld.getMainCharacter().getMapElement();
        MapElement enemyElement = enemyCharacter.getMapElement();
        int distanceToMainCharacter = Math.max(Math.abs(enemyElement.getRow() - mainCharacterElement.getRow()), Math.abs(enemyElement.getColumn() - mainCharacterElement.getColumn()));

        MapElement adjacentSummonElement = this.getAdjacentSummonElement(gameWorld.getMap(), enemyElement);

        if (!isConfused && distanceToMainCharacter > 1 && adjacentSummonElement != null)
        {
            int deltaRow = adjacentSummonElement.getRow() - enemyElement.getRow();
            int deltaColumn = adjacentSummonElement.getColumn() - enemyElement.getColumn();
            return new EnemyMovementAction(new EnemyAIBasedPositionChange(enemyCharacter, deltaRow, deltaColumn));
        }

        return null;
    }

    private MapElement getAdjacentSummonElement(Map map, MapElement enemyElement)
    {
        for (int row = enemyElement.getRow() - 1; row <= enemyElement.getRow() + 1; row++)
        {
            for (int column = enemyElement.getColumn() - 1; column <= enemyElement.getColumn() + 1; column++)
            {
                MapElement element = map.getElement(row, column);

                if (element instanceof SummonedMinionMapElement)
                {
                    return element;
                }
            }
        }

        return null;
    }
}

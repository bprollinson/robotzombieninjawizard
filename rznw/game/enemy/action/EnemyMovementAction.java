package rznw.game.enemy.action;

import rznw.turn.positionchange.EnemyAIBasedPositionChange;
import rznw.turn.positionchange.PositionChange;

public class EnemyMovementAction extends EnemyAction
{
    private EnemyAIBasedPositionChange positionChange;

    public EnemyMovementAction(EnemyAIBasedPositionChange positionChange)
    {
        this.positionChange = positionChange;
    }

    public boolean isPositionChange()
    {
        return true;
    }

    public PositionChange getPositionChange()
    {
        return this.positionChange;
    }

    public boolean isSpell()
    {
        return false;
    }
}

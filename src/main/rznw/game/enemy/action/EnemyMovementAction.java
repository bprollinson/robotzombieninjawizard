package rznw.game.enemy.action;

import rznw.game.enemy.spell.EnemySpell;
import rznw.turn.positionchange.PositionChange;

public class EnemyMovementAction extends EnemyAction
{
    private PositionChange positionChange;

    public EnemyMovementAction(PositionChange positionChange)
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

    public EnemySpell getSpell()
    {
        return null;
    }

    public int getSpellPoints()
    {
        return 0;
    }
}

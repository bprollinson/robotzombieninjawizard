package rznw.game.enemy.action;

import rznw.turn.positionchange.PositionChange;

public class EnemySpellAction extends EnemyAction
{
    public boolean isPositionChange()
    {
        return false;
    }

    public PositionChange getPositionChange()
    {
        return null;
    }

    public boolean isSpell()
    {
        return true;
    }
}

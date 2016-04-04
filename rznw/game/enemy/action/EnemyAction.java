package rznw.game.enemy.action;

import rznw.turn.positionchange.PositionChange;

public abstract class EnemyAction
{
    public abstract boolean isPositionChange();

    public abstract boolean isSpell();

    public abstract PositionChange getPositionChange();
}

package rznw.game.enemy.action;

import rznw.game.enemy.spell.EnemySpell;
import rznw.turn.positionchange.PositionChange;

public abstract class EnemyAction
{
    public abstract boolean isPositionChange();

    public abstract boolean isSpell();

    public abstract PositionChange getPositionChange();

    public abstract EnemySpell getSpell();

    public abstract int getSpellPoints();
}

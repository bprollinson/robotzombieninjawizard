package rznw.game.enemy.action;

import rznw.game.enemy.spell.EnemySpell;
import rznw.turn.positionchange.PositionChange;

public class EnemySpellAction extends EnemyAction
{
    private EnemySpell spell;
    private int spellPoints;

    public EnemySpellAction(EnemySpell spell, int spellPoints)
    {
        this.spell = spell;
        this.spellPoints = spellPoints;
    }

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

    public EnemySpell getSpell()
    {
        return this.spell;
    }

    public int getSpellPoints()
    {
        return this.spellPoints;
    }
}

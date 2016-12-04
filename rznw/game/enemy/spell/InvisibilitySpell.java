package rznw.game.enemy.spell;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;
import rznw.utility.StringUtils;

public class InvisibilitySpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        StringUtils utils = new StringUtils();
        LogRendererFactory.instance().log(utils.UCFirst(enemyCharacter.getLogName()) + " casts invisibility.");

        enemyCharacter.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_INVISIBLE, spellPoints);
        LogRendererFactory.instance().log("Enemy becomes invisible.");
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}

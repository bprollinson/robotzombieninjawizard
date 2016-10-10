package rznw.game.enemy.spell;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;

public class InvisibilitySpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        System.out.println("Enemy is casting invisibility with spell points of: " + spellPoints);

        enemyCharacter.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_INVISIBLE, spellPoints);
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}

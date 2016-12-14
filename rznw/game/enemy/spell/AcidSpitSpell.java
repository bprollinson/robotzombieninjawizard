package rznw.game.enemy.spell;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;
import rznw.utility.StringUtils;

public class AcidSpitSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        StringUtils utils = new StringUtils();
        LogRendererFactory.instance().log(utils.UCFirst(enemyCharacter.getLogName()) + " casts acid spit.");

        int damage = 40 + 10 * spellPoints;

        MainCharacter mainCharacter = gameWorld.getMainCharacter();
        int damageDealt = mainCharacter.damage(damage, enemyCharacter, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
        LogRendererFactory.instance().log("Hit for " + damageDealt + " damage.");

        boolean poisoned = mainCharacter.getStatusEffects().poison();
        if (poisoned)
        {
            LogRendererFactory.instance().log("You become poisoned.");
        }

        boolean confused = mainCharacter.getStatusEffects().confuse();
        if (confused)
        {
            LogRendererFactory.instance().log("You become confused.");
        }
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}

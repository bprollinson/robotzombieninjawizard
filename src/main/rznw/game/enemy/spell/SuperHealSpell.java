package rznw.game.enemy.spell;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;
import rznw.utility.StringUtils;

public class SuperHealSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        StringUtils utils = new StringUtils();
        LogRendererFactory.instance().log(utils.UCFirst(enemyCharacter.getLogName()) + " casts super heal.");

        enemyCharacter.setHP(enemyCharacter.getMaxHP());
        LogRendererFactory.instance().log("Enemy heals all HP.");
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(100 - 10 * spellPoints, 1);
    }
}

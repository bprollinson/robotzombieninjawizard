package rznw.game.enemy.spell;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;

public class SuperHealSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        System.out.println("Enemy is casting super heal with spell points of: " + spellPoints);

        enemyCharacter.setHP(enemyCharacter.getMaxHP());
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(100 - 10 * spellPoints, 1);
    }
}

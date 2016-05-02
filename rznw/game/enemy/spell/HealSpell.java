package rznw.game.enemy.spell;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;

public class HealSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        System.out.println("Enemy is casting heal with spell points of: " + spellPoints);

        enemyCharacter.heal(10 * spellPoints);
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}

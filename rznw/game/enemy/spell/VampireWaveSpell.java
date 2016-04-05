package rznw.game.enemy.spell;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;

public class VampireWaveSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        System.out.println("Enemy is casting vampire wave with spell points of: " + spellPoints);
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}

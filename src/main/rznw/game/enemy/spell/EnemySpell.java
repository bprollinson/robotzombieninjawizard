package rznw.game.enemy.spell;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;

public abstract class EnemySpell
{
    public abstract void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints);

    public abstract int getMPCost(int spellPoints);
}

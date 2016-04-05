package rznw.game.enemy.spell;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class VampireWaveSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        System.out.println("Enemy is casting vampire wave with spell points of: " + spellPoints);

        int damage = 40 + 10 * spellPoints;

        System.out.println("Enemy is dealing " + damage + " damage and healing " + damage + " hp");

        MainCharacter character = gameWorld.getMainCharacter();
        damage = character.damage(damage, enemyCharacter, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);

        enemyCharacter.heal(damage);
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}

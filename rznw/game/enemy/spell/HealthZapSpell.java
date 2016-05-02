package rznw.game.enemy.spell;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class HealthZapSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        System.out.println("Enemy is casting heatlh zap with spell points of: " + spellPoints);

        MainCharacter mainCharacter = gameWorld.getMainCharacter();
        int damage = (int)Math.floor(0.3 * mainCharacter.getHP());
        mainCharacter.damage(damage, enemyCharacter, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}

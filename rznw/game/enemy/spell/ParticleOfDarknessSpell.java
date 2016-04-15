package rznw.game.enemy.spell;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class ParticleOfDarknessSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        System.out.println("You are hit by the particle of darkness with points: " + spellPoints);

        MainCharacter character = gameWorld.getMainCharacter();
        if (character.getHP() > 1)
        {
            character.setHP(1);
        }
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}

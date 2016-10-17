package rznw.game.spell.robot;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapRayTracer;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public class ParalyzingBlastSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Paralyzing Blast";
    }

    public String getDescription()
    {
        return "Damages an enemy and has a chance to stun them. The blast travels in a straight line.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Paralyzing Blast");

        MainCharacter character = gameWorld.getMainCharacter();

        int damage = 20 + 10 * spellPoints;

        Map map = gameWorld.getMap();
        MapElement element = new MapRayTracer(map).findNextElementInDirection(character.getMapElement(), direction);

        if (element.isEnemy())
        {
            System.out.println("Direct hit " + element);

            Character enemy = ((EnemyMapElement)element).getCharacter();
            System.out.println("Before: " + enemy.getHP());
            enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            System.out.println("After: " + enemy.getHP());

            if (!enemy.isDead())
            {
                int probabilityToFreeze = 5 * spellPoints;

                if (RandomNumberGenerator.rollSucceeds(probabilityToFreeze))
                {
                    System.out.println("Enemy paralyzed");
                    enemy.getStatusEffects().freeze();
                }
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int radius = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + (20 + 10 * spellPoints),
            "Chance to paralyze: " + 5 * spellPoints + "%"
        };
    }
}

package rznw.game.spell.ninja;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapRayTracer;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

import java.util.Collection;
import java.util.Iterator;

public class StunBombSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Stun Bomb";
    }

    public String getDescription()
    {
        return "Throws a projectile in a straight line. When this collides with an enemy, it explodes, causing damage to all enemies within a radius of the impact, and has a chance to stun those enemies.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        LogRendererFactory.instance().log("Casting stun bomb.");

        MainCharacter character = gameWorld.getMainCharacter();
        MapElement characterElement = character.getMapElement();

        Map map = gameWorld.getMap();
        MapElement element = new MapRayTracer(map).findNextElementInDirection(characterElement, direction);

        if (element.isEnemy())
        {
            int damage = 50 + 10 * spellPoints;

            Character enemy = ((EnemyMapElement)element).getCharacter();
            damage = enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            LogRendererFactory.instance().log("Dealt " + damage + " damage to " + enemy.getLogName() + ".");
        }

        int radius = 1 + (int)Math.floor(spellPoints / 4);
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(element.getRow() - radius, element.getColumn() - radius, element.getRow() + radius, element.getColumn() + radius);
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();

            int stunProbability = 5 * spellPoints;
            if (RandomNumberGenerator.rollSucceeds(stunProbability))
            {
                enemy.getStatusEffects().freeze();
                LogRendererFactory.instance().log("Enemy " + enemy.getLogName() + " stunned.");
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
            "Damage: " + (50 + 10 * spellPoints),
            "Stun radius: " + radius,
            "Chance to stun: " + 5 * spellPoints + "%"
        };
    }
}

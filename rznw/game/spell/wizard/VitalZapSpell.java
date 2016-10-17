package rznw.game.spell.wizard;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapRayTracer;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;

public class VitalZapSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Vital Zap";
    }

    public String getDescription()
    {
        return "Shoots a projectile in the chosen direction. This projectile deals damage to an opponent equal to a percentage of that opponent's remaining HP. The percentage increases as your spell level increases.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Vital Zap");

        MainCharacter character = gameWorld.getMainCharacter();

        int damagePercentage = 5 * spellPoints;

        Map map = gameWorld.getMap();
        MapElement element = new MapRayTracer(map).findNextElementInDirection(character.getMapElement(), direction);

        if (element.isEnemy())
        {
            System.out.println("Hit an enemy");

            Character enemy = ((EnemyMapElement)element).getCharacter();
            System.out.println("Before: " + enemy.getHP());
            int damage = (int)Math.floor(damagePercentage / 100.0 * enemy.getHP());
            enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            System.out.println("After: " + enemy.getHP());
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + 5 * spellPoints + "%"
        };
    }
}

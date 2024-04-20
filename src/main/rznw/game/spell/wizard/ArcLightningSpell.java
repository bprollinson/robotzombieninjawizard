package rznw.game.spell.wizard;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapRayTracer;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class ArcLightningSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Arc Lightning";
    }

    public String getDescription()
    {
        return "Shoots a projectile that chains between enemies. Travels in a straight line, then jumps between enemies within a radius of eachother. Attacks each enemy at most once.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        LogRendererFactory.instance().log("Casting arc lightning.");

        int damage = 50 + 10 * spellPoints;
        int radius = 1 + (int)Math.floor(spellPoints / 4);
        Map map = gameWorld.getMap();
        MainCharacter character = gameWorld.getMainCharacter();

        MapElement element = new MapRayTracer(map).findNextElementInDirection(character.getMapElement(), direction);

        if (element.isEnemy())
        {
            HashSet<EnemyMapElement> currentSet = new HashSet<EnemyMapElement>();
            HashSet<EnemyMapElement> totalSet = new HashSet<EnemyMapElement>();

            currentSet.add((EnemyMapElement)element);
            totalSet.add((EnemyMapElement)element);

            while (currentSet.size() > 0)
            {
                this.damageAllInSet(currentSet, damage, character, gameWorld);

                currentSet = this.getNextCurrentSet(currentSet, totalSet, radius, map);
                totalSet.addAll(currentSet);
            }
        }
    }

    private void damageAllInSet(HashSet<EnemyMapElement> currentSet, int damage, MainCharacter character, GameWorld gameWorld)
    {
        for (Iterator iterator = currentSet.iterator(); iterator.hasNext();)
        {
            EnemyMapElement enemyElement = (EnemyMapElement)iterator.next();
            int damageDealt = enemyElement.getCharacter().damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            LogRendererFactory.instance().log("Dealt " + damageDealt + " damage to " + enemyElement.getCharacter().getLogName() + ".");
        }
    }

    private HashSet<EnemyMapElement> getNextCurrentSet(HashSet<EnemyMapElement> currentSet, HashSet<EnemyMapElement> totalSet, int radius, Map map)
    {
        HashSet<EnemyMapElement> nextSet = new HashSet<EnemyMapElement>();

        for (Iterator iterator = currentSet.iterator(); iterator.hasNext();)
        {
            EnemyMapElement enemyElement = (EnemyMapElement)iterator.next();

            Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(enemyElement.getRow() - radius, enemyElement.getColumn() - radius, enemyElement.getRow() + radius, enemyElement.getColumn() + radius);
            for (Iterator iterator2 = enemies.iterator(); iterator2.hasNext();)
            {
                EnemyCharacter enemy = (EnemyCharacter)iterator2.next();
                nextSet.add((EnemyMapElement)enemy.getMapElement());
            }
        }

        nextSet.removeAll(totalSet);
        return nextSet;
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int damage = 50 + 10 * spellPoints;
        int radius = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + damage,
            "Jump radius: " + radius
        };
    }
}

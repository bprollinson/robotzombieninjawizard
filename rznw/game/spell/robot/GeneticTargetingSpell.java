package rznw.game.spell.robot;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.ClosestEnemiesCalculator;
import rznw.map.Map;
import rznw.map.MapCharacterScraper;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GeneticTargetingSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Genetic Targeting";
    }

    public String getDescription()
    {
        return "Deals damage to the nearest enemy, and all on the current dungeon level of the same type. The nearest enemies is targeted automatically regardless of position.";
    }

    public boolean canCast(GameWorld gameWorld, int spellPoints)
    {
        if (!super.canCast(gameWorld, spellPoints))
        {
            return false;
        }

        return new MapCharacterScraper().getEnemies(gameWorld.getMap()).size() > 0;
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting genetic targeting.");

        List<java.util.Map.Entry<EnemyCharacter, Double>> minDistanceList = new ClosestEnemiesCalculator().getSortedEnemiesList(gameWorld.getMap(), gameWorld.getMainCharacter().getMapElement());

        double minDistance = minDistanceList.get(0).getValue();

        int maxSameIndex = 0;
        for (int i = 1; i < minDistanceList.size(); i++)
        {
            double distance = minDistanceList.get(i).getValue();

            if (Math.abs(distance - minDistance) < 0.01)
            {
                maxSameIndex = i;
            }
            else
            {
                break;
            }
        }

        int randomIndex = RandomNumberGenerator.randomInteger(0, maxSameIndex);

        EnemyCharacter targetedEnemy = minDistanceList.get(randomIndex).getKey();

        int damage = 5 * spellPoints;

        Collection<EnemyCharacter> enemies = new MapCharacterScraper().getAllEnemiesOfType(gameWorld.getMap(), targetedEnemy.getClass());
        Iterator<EnemyCharacter> iterator = enemies.iterator();
        while (iterator.hasNext())
        {
            EnemyCharacter enemy = iterator.next();

            int damageDealt = enemy.damage(damage, gameWorld.getMainCharacter(), gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            LogRendererFactory.instance().log("Dealt " + damageDealt + " damage to " + enemy.getLogName() + ".");
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
            "Damage: " + (5 * spellPoints)
        };
    }
}

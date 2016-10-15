package rznw.game.spell.robot;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapCharacterScraper;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

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
        System.out.println("Casting Genetic Targeting");

        Map map = gameWorld.getMap();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(0, 0, Map.NUM_ROWS - 1, Map.NUM_COLUMNS - 1);
        HashMap<EnemyCharacter, Double> minDistanceMap = new HashMap<EnemyCharacter, Double>();

        MainCharacter character = gameWorld.getMainCharacter();
        MapElement characterElement = character.getMapElement();

        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            MapElement enemyElement = enemy.getMapElement();

            double distance = Math.sqrt(Math.pow(enemyElement.getRow() - characterElement.getRow(), 2) + Math.pow(enemyElement.getColumn() - characterElement.getColumn(), 2));
            minDistanceMap.put(enemy, distance);
        }

        List<java.util.Map.Entry<EnemyCharacter, Double>> minDistanceList = new LinkedList<java.util.Map.Entry<EnemyCharacter, Double>>(minDistanceMap.entrySet());
        Collections.sort(minDistanceList, new Comparator<java.util.Map.Entry<EnemyCharacter, Double>>()
        {
            public int compare(java.util.Map.Entry<EnemyCharacter, Double> o1, java.util.Map.Entry<EnemyCharacter, Double> o2)
            {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        double minDistance = minDistanceList.get(0).getValue();
        System.out.println("Main distance: " + minDistance);

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

        System.out.println("Max same index: " + maxSameIndex);
        int randomIndex = RandomNumberGenerator.randomInteger(0, maxSameIndex);

        EnemyCharacter targetedEnemy = minDistanceList.get(randomIndex).getKey();
        System.out.println("Targeted enemy: " + targetedEnemy);

        int damage = 5 * spellPoints;

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getElement(row, column);

                if (element != null && element.isEnemy())
                {
                    EnemyCharacter enemy = (EnemyCharacter)((EnemyMapElement)element).getCharacter();

                    if (enemy.getClass().equals(targetedEnemy.getClass()))
                    {
                        System.out.println("Finding an enemy of the same type at: " + row + ", " + column + " : " + element);

                        System.out.println("HP before: " + enemy.getHP());
                        enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
                        System.out.println("HP after: " + enemy.getHP());
                    }
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
            "Damage: " + (5 * spellPoints)
        };
    }
}

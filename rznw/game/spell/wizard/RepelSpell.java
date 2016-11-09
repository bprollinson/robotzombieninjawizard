package rznw.game.spell.wizard;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.MapElementSetter;
import rznw.map.Map;
import rznw.map.element.MapElement;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

public class RepelSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Repel";
    }

    public String getDescription()
    {
        return "Pushes nearby enemies away. The distance and radius of effect increase with your spell level.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Repel");

        MainCharacter character = gameWorld.getMainCharacter();
        Map map = gameWorld.getMap();

        int radius = 1 + (int)Math.floor(spellPoints / 4);
        int pushDistance = 1 + (int)Math.floor(spellPoints / 4);

        System.out.println("Repel radius: " + radius);
        System.out.println("Repel distance: " + pushDistance);

        MapElement characterElement = character.getMapElement();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);

        HashMap<MapElement, Double> distanceMap = new HashMap<MapElement, Double>();
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            MapElement enemyMapElement = enemy.getMapElement();

            double distance = Math.sqrt(Math.pow(characterElement.getRow() - enemyMapElement.getRow(), 2) + Math.pow(characterElement.getColumn() - enemyMapElement.getColumn(), 2));
            distanceMap.put(enemyMapElement, distance);
        }

        List<java.util.Map.Entry<MapElement, Double>> distanceList = new LinkedList<java.util.Map.Entry<MapElement, Double>>(distanceMap.entrySet());
        Collections.sort(distanceList, new Comparator<java.util.Map.Entry<MapElement, Double>>()
        {
            public int compare(java.util.Map.Entry<MapElement, Double> o1, java.util.Map.Entry<MapElement, Double> o2)
            {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });

        HashMap<MapElement, int[]> directionMap = new HashMap<MapElement, int[]>();
        for (int i = 0; i < distanceList.size(); i++)
        {
            MapElement key = distanceList.get(i).getKey();

            int deltaRow = 0;
            if (key.getRow() < characterElement.getRow())
            {
                deltaRow = -1;
            }
            if (key.getRow() > characterElement.getRow())
            {
                deltaRow = 1;
            }

            int deltaColumn = 0;
            if (key.getColumn() < characterElement.getColumn())
            {
                deltaColumn = -1;
            }
            if (key.getColumn() > characterElement.getColumn())
            {
                deltaColumn = 1;
            }

            directionMap.put(key, new int[]{deltaRow, deltaColumn});
        }

        for (int i = 0; i < pushDistance; i++)
        {
            System.out.println("In pushDistance iteration");

            for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
            {
                System.out.println("Have an enemy");
                EnemyCharacter enemy = (EnemyCharacter)iterator.next();
                MapElement enemyElement = enemy.getMapElement();
                int[] directions = directionMap.get(enemyElement);

                int oldRow = enemyElement.getRow();
                int oldColumn = enemyElement.getColumn();
                int newRow = oldRow + directions[0];
                int newColumn = oldColumn + directions[1];

                if (map.getElement(newRow, newColumn) == null)
                {
                    map.setElement(oldRow, oldColumn, null);
                    MapElementSetter.setElement(map, enemyElement, newRow, newColumn);
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
        int pushDistance = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Repel radius: " + radius,
            "Repel distance: " + pushDistance
        };
    }
}

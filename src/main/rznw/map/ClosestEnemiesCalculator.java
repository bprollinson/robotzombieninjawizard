package rznw.map;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.element.MapElement;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ClosestEnemiesCalculator
{
    public List<java.util.Map.Entry<EnemyCharacter, Double>> getSortedEnemiesList(Map map, MapElement element)
    {
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(0, 0, Map.NUM_ROWS - 1, Map.NUM_COLUMNS - 1);

        HashMap<EnemyCharacter, Double> minDistanceMap = new HashMap<EnemyCharacter, Double>();
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            MapElement enemyElement = enemy.getMapElement();

            double distance = Math.sqrt(Math.pow(enemyElement.getRow() - element.getRow(), 2) + Math.pow(enemyElement.getColumn() - element.getColumn(), 2));
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

        return minDistanceList;
    }
}

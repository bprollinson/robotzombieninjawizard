package rznw.map;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.element.MapElement;
import rznw.map.element.Void;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TeleportSquareCalculator
{
    private GameWorld gameWorld;

    public TeleportSquareCalculator(GameWorld gameWorld)
    {
        this.gameWorld = gameWorld;
    }

    public MapElement getMapElementWithSafetyPercentage(double safetyPercentage)
    {
        HashMap<MapElement, Double> minDistanceMap = new HashMap<MapElement, Double>();

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = this.gameWorld.getMap().getElement(row, column);
                if (element == null)
                {
                    minDistanceMap.put(new Void(row, column), this.getMinDistanceToPosition(row, column));
                }
            }
        }

        List<java.util.Map.Entry<MapElement, Double>> minDistanceList = new LinkedList<java.util.Map.Entry<MapElement, Double>>(minDistanceMap.entrySet());
        Collections.sort(minDistanceList, new Comparator<java.util.Map.Entry<MapElement, Double>>()
        {
            public int compare(java.util.Map.Entry<MapElement, Double> o1, java.util.Map.Entry<MapElement, Double> o2)
            {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        System.out.println("safety percentage: " + safetyPercentage);
        int position = (int)Math.floor(safetyPercentage / 100 * (minDistanceList.size() - 1));

        System.out.println("position vs size: " + position + "-" + minDistanceList.size());
        System.out.println("selected enemy distance: " + minDistanceList.get(position).getValue());

        return minDistanceList.get(position).getKey();
    }

    private double getMinDistanceToPosition(int row, int column)
    {
        double result = 1000;

        Map map = this.gameWorld.getMap();
        Collection<EnemyCharacter> enemies = map.getEnemies();
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            MapElement mapElement = enemy.getMapElement();

            double distance = Math.sqrt(Math.pow(mapElement.getRow() - row, 2) + Math.pow(mapElement.getColumn() - column, 2));
            if (distance < result)
            {
                result = distance;
            }
        }

        return result;
    }
}

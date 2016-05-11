package rznw.game;

import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.ShortestPathCalculator;
import rznw.map.element.MapElement;
import rznw.map.element.Void;
import rznw.map.generator.MapPoint;
import rznw.map.generator.direction.PathDirection;
import rznw.map.generator.path.MapPath;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class SummonedCharacter extends Character
{
    public void generateMapElement(int row, int column)
    {
    }

    public int getMaxHP()
    {
        return 100;
    }

    public int getMaxMP()
    {
        return 100;
    }

    public int getDamage()
    {
        return 10;
    }

    public boolean meleeAttackHits()
    {
        return true;
    }

    public boolean dodgesAttack()
    {
        return false;
    }

    public EnemyAIBasedPositionChange getPositionChange(GameWorld gameWorld)
    {
        HashMap<MapElement, Double> minDistanceMap = new HashMap<MapElement, Double>();

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = gameWorld.getMap().getElement(row, column);
                if (element != null && element.isEnemy())
                {
                    double distance = Math.sqrt(Math.pow(element.getRow() - this.getMapElement().getRow(), 2) + Math.pow(element.getColumn() - this.getMapElement().getColumn(), 2));
                    minDistanceMap.put(new Void(row, column), distance);
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

        if (minDistanceList.size() == 0)
        {
            return new EnemyAIBasedPositionChange(this, 0, 0);
        }

        MapElement closestElement = minDistanceList.get(0).getKey();

        MapPoint startPoint = new MapPoint(this.getMapElement().getColumn(), this.getMapElement().getRow());
        MapPoint endPoint = new MapPoint(closestElement.getColumn(), closestElement.getRow());
        ShortestPathCalculator pathCalculator = new ShortestPathCalculator(gameWorld.getMap(), false, true);
        MapPath path = pathCalculator.calculateShortestPath(startPoint, endPoint);

        PathDirection firstDirection = path.getDirection(0);

        return new EnemyAIBasedPositionChange(this, firstDirection.getDeltaRow(), firstDirection.getDeltaColumn());
    }
}

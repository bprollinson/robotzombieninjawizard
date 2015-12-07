package rznw.game;

import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.map.element.Void;
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
                if (element instanceof EnemyMapElement)
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

        MapElement closestElement = minDistanceList.get(0).getKey();

        int deltaRow = 0;
        if (closestElement.getRow() < this.getMapElement().getRow())
        {
            deltaRow = -1;
        }
        if (closestElement.getRow() > this.getMapElement().getRow())
        {
            deltaRow = 1;
        }

        int deltaColumn = 0;
        if (closestElement.getColumn() < this.getMapElement().getColumn())
        {
            deltaColumn = -1;
        }
        if (closestElement.getColumn() > this.getMapElement().getColumn())
        {
            deltaColumn = 1;
        }

        return new EnemyAIBasedPositionChange(this, deltaRow, deltaColumn);
    }
}

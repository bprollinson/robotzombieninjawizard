package rznw.map;

import rznw.map.element.MapElement;
import rznw.map.element.Void;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ClosestSquareCalculator
{
    private GameWorld gameWorld;

    public ClosestSquareCalculator(GameWorld gameWorld)
    {
        this.gameWorld = gameWorld;
    }

    public MapElement getClosestPositionElement(MapElement startingPositionElement)
    {
        HashMap<MapElement, Double> distanceMap = new HashMap<MapElement, Double>();

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                if (gameWorld.getMap().getElement(row, column) == null)
                {
                    double distance = Math.sqrt(Math.pow(startingPositionElement.getRow() - row, 2) + Math.pow(startingPositionElement.getColumn() - column, 2));
                    distanceMap.put(new Void(row, column), distance);
                }
            }
        }

        List<java.util.Map.Entry<MapElement, Double>> distanceList = new LinkedList<java.util.Map.Entry<MapElement, Double>>(distanceMap.entrySet());
        Collections.sort(distanceList, new Comparator<java.util.Map.Entry<MapElement, Double>>()
        {
            public int compare(java.util.Map.Entry<MapElement, Double> o1, java.util.Map.Entry<MapElement, Double> o2)
            {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        return distanceList.get(0).getKey();
    }
}

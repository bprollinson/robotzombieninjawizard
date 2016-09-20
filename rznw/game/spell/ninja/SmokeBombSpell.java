package rznw.game.spell.ninja;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import rznw.game.StatusEffects;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.Void;

public class SmokeBombSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Smoke Bomb";
    }

    public String getDescription()
    {
        return "The next time you are damaged by an enemy, you have a chance to escape to a safe location.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Smoke Bomb");

        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().setStatusEffect(StatusEffects.EFFECT_SMOKE_BOMB, true);
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public static void escape(GameWorld gameWorld)
    {
        System.out.println("Escaping with smoke bomb");

        MainCharacter character = gameWorld.getMainCharacter();
        int spellPoints = character.getSpellPoints(12);

        MapElement newPositionElement = SmokeBombSpell.getNewPositionElement(gameWorld, spellPoints);

        Map map = gameWorld.getMap();
        int oldRow = character.getMapElement().getRow();
        int oldColumn = character.getMapElement().getColumn();

        map.setElement(oldRow, oldColumn, null);
        MapElement characterMapElement = character.getMapElement();
        characterMapElement.setRow(newPositionElement.getRow());
        characterMapElement.setColumn(newPositionElement.getColumn());
        map.setElement(newPositionElement.getRow(), newPositionElement.getColumn(), characterMapElement);
        map.setElementVisited(character, newPositionElement.getRow(), newPositionElement.getColumn());
    }

    private static MapElement getNewPositionElement(GameWorld gameWorld, int spellPoints)
    {
        HashMap<MapElement, Double> minDistanceMap = new HashMap<MapElement, Double>();

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = gameWorld.getMap().getElement(row, column);
                if (element == null)
                {
                    minDistanceMap.put(new Void(row, column), SmokeBombSpell.getMinDistanceToPosition(gameWorld, row, column));
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

        double positionPercentage = Math.floor(50 + 50 * Math.min(spellPoints / 20.0, 1));
        System.out.println("position percentage: " + positionPercentage);
        int position = (int)Math.floor(positionPercentage / 100 * (minDistanceList.size() - 1));

        System.out.println("position vs size: " + position + "-" + minDistanceList.size());
        System.out.println("nearest enemy distance: " + minDistanceList.get(position).getValue());

        return minDistanceList.get(position).getKey();
    }

    private static double getMinDistanceToPosition(GameWorld gameWorld, int row, int column)
    {
        double result = 1000;

        Map map = gameWorld.getMap();
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

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        double positionPercentage = Math.floor(50 + 50 * Math.min(spellPoints / 20.0, 1));

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Chance to escape: " + 5 * spellPoints + "%",
            "Safety percentage: " + positionPercentage + "%"
        };
    }
}

package rznw.game.enemy.spell;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.Werewolf;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.Void;

public class SummonBeastSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        System.out.println("Enemy is casting summon beast with spell points of: " + spellPoints);

        MapElement element = this.getPositionElement(gameWorld, enemyCharacter);
        System.out.println("Summoning werewolf at: " + element.getRow() + ", " + element.getColumn());

        Werewolf werewolf = new Werewolf(spellPoints);
        werewolf.generateMapElement(element.getRow(), element.getColumn());
        gameWorld.getMap().setElement(werewolf.getMapElement().getRow(), werewolf.getMapElement().getColumn(), werewolf.getMapElement());
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    private MapElement getPositionElement(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        HashMap<MapElement, Double> distanceMap = new HashMap<MapElement, Double>();
        MapElement characterElement = enemyCharacter.getMapElement();

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                if (gameWorld.getMap().getElement(row, column) == null)
                {
                    double distance = Math.sqrt(Math.pow(characterElement.getRow() - row, 2) + Math.pow(characterElement.getColumn() - column, 2));
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

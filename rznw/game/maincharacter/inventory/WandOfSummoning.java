package rznw.game.maincharacter.inventory;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import rznw.game.SummonedGolem;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.SummonedGolemMapElement;
import rznw.map.element.Void;

public class WandOfSummoning extends Weapon
{
    public String getDisplayName()
    {
        return "Wand of Summoning";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "Summons golems on contact",
            "",
            "Value: " + this.getValue()
        };
    }

    public int getDamage()
    {
        return 8;
    }

    public int getValue()
    {
        return 400;
    }

    public void damagedEnemyCharacter(MainCharacter mainCharacter, EnemyCharacter enemyCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Summon golem via wand of summoning");

        MapElement element = this.getPositionElement(gameWorld);
        System.out.println("Creating golem at: " + element.getRow() + ", " + element.getColumn());

        int spellPoints = 5;
        int maxHP = 100 + 5 * spellPoints;
        System.out.println("Max HP is: " + maxHP);

        SummonedGolem golem = new SummonedGolem(maxHP);
        SummonedGolemMapElement golemElement = new SummonedGolemMapElement(element.getRow(), element.getColumn(), golem);
        golem.setMapElement(golemElement);
        gameWorld.getMap().setElement(golemElement.getRow(), golemElement.getColumn(), golemElement);
    }

    private MapElement getPositionElement(GameWorld gameWorld)
    {
        HashMap<MapElement, Double> distanceMap = new HashMap<MapElement, Double>();
        MapElement characterElement = gameWorld.getMainCharacter().getMapElement();

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

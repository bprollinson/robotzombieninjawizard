package rznw.game.spell.zombie;

import rznw.game.SummonedCharacter;
import rznw.game.SummonedZombie;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.SummonedZombieMapElement;
import rznw.map.element.Void;
import rznw.utility.RandomNumberGenerator;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MultiplyZombiesSpell extends UndirectedSpell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Multiply Zombies");

        Map map = gameWorld.getMap();

        int multiplyProbability = 5 * spellPoints;
        int maxHP = 100 + 5 * gameWorld.getMainCharacter().getSpellPoints(12);
        System.out.println("Multiply zombie max HP: " + maxHP);

        Collection<SummonedCharacter> summons = gameWorld.getMap().getSummons();
        for (Iterator iterator = summons.iterator(); iterator.hasNext();)
        {
            System.out.println("Attempting to multiply a zombie");
            SummonedCharacter summon = (SummonedCharacter)iterator.next();

            if (RandomNumberGenerator.rollSucceeds(multiplyProbability))
            {
                System.out.println("Zombie is multiplying");
                MapElement element = this.getPositionElement(gameWorld, summon.getMapElement());

                SummonedZombie zombie = new SummonedZombie(maxHP);
                SummonedZombieMapElement zombieElement = new SummonedZombieMapElement(element.getRow(), element.getColumn(), zombie);
                zombie.setMapElement(zombieElement);
                gameWorld.getMap().setElement(zombieElement.getRow(), zombieElement.getColumn(), zombieElement);
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    private MapElement getPositionElement(GameWorld gameWorld, MapElement startingPosition)
    {
        HashMap<MapElement, Double> distanceMap = new HashMap<MapElement, Double>();

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                if (gameWorld.getMap().getElement(row, column) == null)
                {
                    double distance = Math.sqrt(Math.pow(startingPosition.getRow() - row, 2) + Math.pow(startingPosition.getColumn() - column, 2));
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

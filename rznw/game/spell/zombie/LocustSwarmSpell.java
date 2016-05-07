package rznw.game.spell.zombie;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;

public class LocustSwarmSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Locust Swarm";
    }

    public String getDescription()
    {
        return "Shoots a projectile that chains between enemies. Travels in a straight line, then jumps between enemies within a radius of eachother. Attacks each enemy at most once.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Locust Swarm");

        int damage = 50 + 10 * spellPoints;
        int radius = 1 + (int)Math.floor(spellPoints / 4);
        System.out.println("Radius is: " + radius);
        Map map = gameWorld.getMap();
        MainCharacter character = gameWorld.getMainCharacter();

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(0, 0, direction);

        boolean objectFound = false;
        int row = character.getMapElement().getRow();
        int column = character.getMapElement().getColumn();

        while (!objectFound)
        {
            row += positionChange.getDeltaRow();
            column += positionChange.getDeltaColumn();

            objectFound = map.getElement(row, column) != null;
        }

        MapElement element = map.getElement(row, column);
        if (element instanceof EnemyMapElement)
        {
            HashSet<EnemyMapElement> currentSet = new HashSet<EnemyMapElement>();
            HashSet<EnemyMapElement> totalSet = new HashSet<EnemyMapElement>();

            currentSet.add((EnemyMapElement)element);
            totalSet.add((EnemyMapElement)element);

            while (currentSet.size() > 0)
            {
                System.out.println("In Arc Lightning iterator");
                this.damageAllInSet(currentSet, damage, character, gameWorld);

                currentSet = this.getNextCurrentSet(currentSet, totalSet, radius, map);
                totalSet.addAll(currentSet);
            }
        }
    }

    private void damageAllInSet(HashSet<EnemyMapElement> currentSet, int damage, MainCharacter character, GameWorld gameWorld)
    {
        for (Iterator iterator = currentSet.iterator(); iterator.hasNext();)
        {
            EnemyMapElement enemyElement = (EnemyMapElement)iterator.next();
            System.out.println("Damaging enemy at: " + enemyElement.getRow() + " , " + enemyElement.getColumn());
            System.out.println("HP before: " + enemyElement.getCharacter().getHP());
            enemyElement.getCharacter().damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            System.out.println("HP after: " + enemyElement.getCharacter().getHP());
        }
    }

    private HashSet<EnemyMapElement> getNextCurrentSet(HashSet<EnemyMapElement> currentSet, HashSet<EnemyMapElement> totalSet, int radius, Map map)
    {
        HashSet<EnemyMapElement> nextSet = new HashSet<EnemyMapElement>();

        for (Iterator iterator = currentSet.iterator(); iterator.hasNext();)
        {
            EnemyMapElement enemyElement = (EnemyMapElement)iterator.next();

            Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(enemyElement.getRow() - radius, enemyElement.getColumn() - radius, enemyElement.getRow() + radius, enemyElement.getColumn() + radius);
            for (Iterator iterator2 = enemies.iterator(); iterator2.hasNext();)
            {
                EnemyCharacter enemy = (EnemyCharacter)iterator2.next();
                nextSet.add((EnemyMapElement)enemy.getMapElement());
            }
        }

        nextSet.removeAll(totalSet);
        return nextSet;
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int damage = 50 + 10 * spellPoints;
        int radius = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + damage,
            "Jump radius: " + radius
        };
    }
}

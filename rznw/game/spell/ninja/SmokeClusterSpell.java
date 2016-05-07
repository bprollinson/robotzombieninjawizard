package rznw.game.spell.ninja;

import java.util.Collection;
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
import rznw.utility.RandomNumberGenerator;

public class SmokeClusterSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Smoke Cluster";
    }

    public String getDescription()
    {
        return "Throws a projectile in a straight line. When this collides with an enemy, it explodes, confusing all enemies within a radius of the impact.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Smoke Bomb");

        MainCharacter character = gameWorld.getMainCharacter();

        int damage = 50 + 10 * spellPoints;

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(0, 0, direction);

        boolean objectFound = false;
        int row = character.getMapElement().getRow();
        int column = character.getMapElement().getColumn();

        while (!objectFound)
        {
            row += positionChange.getDeltaRow();
            column += positionChange.getDeltaColumn();

            Map map = gameWorld.getMap();
            MapElement element = map.getElement(row, column);

            if (element != null)
            {
                objectFound = true;
            }

            if (element instanceof EnemyMapElement)
            {
                System.out.println("Direct hit " + element);

                Character enemy = ((EnemyMapElement)element).getCharacter();
                System.out.println("Before: " + enemy.getHP());
                enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
                System.out.println("After: " + enemy.getHP());
            }

            if (element != null)
            {
                MapElement characterElement = character.getMapElement();

                int radius = 1 + (int)Math.floor(spellPoints / 4);
                Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(element.getRow() - radius, element.getColumn() - radius, element.getRow() + radius, element.getColumn() + radius);
                for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
                {
                    System.out.println("Indirect hit " + element);
                    EnemyCharacter enemy = (EnemyCharacter)iterator.next();

                    int confuseProbability = 5 * spellPoints;
                    if (RandomNumberGenerator.rollSucceeds(confuseProbability))
                    {
                        System.out.println("Confused");

                        enemy.getStatusEffects().confuse();
                    }
                    else
                    {
                        System.out.println("Not confused");
                    }
                }
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int radius = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + (50 + 10 * spellPoints),
            "Confusion radius: " + radius,
            "Chance to confuse: " + 5 * spellPoints + "%"
        };
    }
}

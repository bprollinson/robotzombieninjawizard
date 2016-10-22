package rznw.game.spell.ninja;

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

import java.util.Collection;
import java.util.Iterator;

public class StunBombSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Stun Bomb";
    }

    public String getDescription()
    {
        return "Throws a projectile in a straight line. When this collides with an enemy, it explodes, causing damage to all enemies within a radius of the impact, and has a chance to stun those enemies.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Stun Bomb");

        MainCharacter character = gameWorld.getMainCharacter();

        int damage = 50 + 10 * spellPoints;

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(direction);

        Map map = gameWorld.getMap();
        int row = character.getMapElement().getRow() + positionChange.getDeltaRow();
        int column = character.getMapElement().getColumn() + positionChange.getDeltaColumn();

        while (map.getElement(row, column) == null)
        {
            row += positionChange.getDeltaRow();
            column += positionChange.getDeltaColumn();
        }

        MapElement element = map.getElement(row, column);

        if (element.isEnemy())
        {
            System.out.println("Direct hit " + element);

            Character enemy = ((EnemyMapElement)element).getCharacter();
            System.out.println("Before: " + enemy.getHP());
            enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            System.out.println("After: " + enemy.getHP());
        }

        MapElement characterElement = character.getMapElement();

        int radius = 1 + (int)Math.floor(spellPoints / 4);
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(element.getRow() - radius, element.getColumn() - radius, element.getRow() + radius, element.getColumn() + radius);
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            System.out.println("Attempting to stun " + enemy);

            int stunProbability = 5 * spellPoints;
            if (RandomNumberGenerator.rollSucceeds(stunProbability))
            {
                System.out.println("Stunned");
                enemy.getStatusEffects().freeze();
            }
            else
            {
                System.out.println("Not stunned");
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
            "Stun radius: " + radius,
            "Chance to stun: " + 5 * spellPoints + "%"
        };
    }
}

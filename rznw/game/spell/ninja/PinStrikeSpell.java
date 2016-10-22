package rznw.game.spell.ninja;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapElementSetter;
import rznw.map.MapRayTracer;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;

public class PinStrikeSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Pin Strike";
    }

    public String getDescription()
    {
        return "Throws a projectile in a straight line. When this collides with an enemy, that enemy damaged and is pushed back.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Pin Strike");

        MainCharacter character = gameWorld.getMainCharacter();

        int damage = 10 + 10 * spellPoints;

        Map map = gameWorld.getMap();
        MapElement element = new MapRayTracer(map).findNextElementInDirection(character.getMapElement(), direction);

        if (element.isEnemy())
        {
            System.out.println("Direct hit " + element);

            Character enemy = ((EnemyMapElement)element).getCharacter();
            System.out.println("Before: " + enemy.getHP());
            enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            System.out.println("After: " + enemy.getHP());

            int distance = 1 + (int)Math.floor(spellPoints / 4);
            System.out.println("Maximum distance: " + distance);

            SpellBasedPositionChange positionChange = new SpellBasedPositionChange(direction);
            int row = element.getRow();
            int column = element.getColumn();

            for (int i = 0; i < distance; i++)
            {
                row += positionChange.getDeltaRow();
                column += positionChange.getDeltaColumn();

                MapElement test = map.getElement(row, column);
                if (test != null)
                {
                    break;
                }

                map.setElement(element.getRow(), element.getColumn(), null);
                MapElementSetter.setElement(map, element, row, column);
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int distance = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + (10 + 10 * spellPoints),
            "Pushback distance: " + distance
        };
    }
}

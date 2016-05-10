package rznw.game.spell.ninja;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
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

            if (element == null)
            {
                continue;
            }

            objectFound = true;

            if (element.isEnemy())
            {
                System.out.println("Direct hit " + element);

                Character enemy = ((EnemyMapElement)element).getCharacter();
                System.out.println("Before: " + enemy.getHP());
                enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
                System.out.println("After: " + enemy.getHP());

                int distance = 1 + (int)Math.floor(spellPoints / 4);
                System.out.println("Maximum distance: " + distance);

                row = element.getRow();
                column = element.getColumn();

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
                    map.setElement(row, column, element);
                    element.setRow(row);
                    element.setColumn(column);
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
        int distance = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + (10 + 10 * spellPoints),
            "Pushback distance: " + distance
        };
    }
}

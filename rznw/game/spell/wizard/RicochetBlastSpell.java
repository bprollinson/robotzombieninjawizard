package rznw.game.spell.wizard;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;

public class RicochetBlastSpell extends DirectedSpell
{
    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Ricochet Blast");

        MainCharacter character = gameWorld.getMainCharacter();

        System.out.println("Main character position: " + character.getMapElement().getRow() + ", " + character.getMapElement().getColumn());

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
                System.out.println("Found element at: " + row + ", " + column);
                objectFound = true;
            }

            if (element instanceof EnemyMapElement)
            {
                System.out.println("Hit an enemy!");
                int damage = 10 * spellPoints;

                while (element != null)
                {
                    element = this.ricochetEnemy(element, damage, character, gameWorld, positionChange.getDeltaRow(), positionChange.getDeltaColumn(), map);
                }
            }
        }
    }

    private MapElement ricochetEnemy(MapElement element, int damage, MainCharacter character, GameWorld gameWorld, int deltaRow, int deltaColumn, Map map)
    {
        System.out.println("Performing a ricochet");
        System.out.println("HP before: " + ((EnemyMapElement)element).getCharacter().getHP());
        ((EnemyMapElement)element).getCharacter().damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
        System.out.println("HP after: " + ((EnemyMapElement)element).getCharacter().getHP());

        while (true)
        {
            int newRow = element.getRow() + deltaRow;
            int newColumn = element.getColumn() + deltaColumn;

            if (map.getElement(newRow, newColumn) instanceof EnemyMapElement)
            {
                return map.getElement(newRow, newColumn);
            }

            if (map.getElement(newRow, newColumn) != null)
            {
                return null;
            }

            map.setElement(element.getRow(), element.getColumn(), null);
            element.setRow(newRow);
            element.setColumn(newColumn);
            map.setElement(newRow, newColumn, element);
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + 10 * spellPoints
        };
    }
}

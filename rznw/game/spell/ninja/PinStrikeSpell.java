package rznw.game.spell.ninja;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;

public class PinStrikeSpell extends Spell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Pin Strike");

        MainCharacter character = gameWorld.getMainCharacter();

        int damage = 10 + 10 * spellPoints;

        int deltaRow = 0;
        int deltaColumn = 0;

        switch(direction)
        {
            case Spell.DIRECTION_UP:
                deltaRow = -1;
                break;
            case Spell.DIRECTION_DOWN:
                deltaRow = 1;
                break;
            case Spell.DIRECTION_LEFT:
                deltaColumn = -1;
                break;
            case Spell.DIRECTION_RIGHT:
                deltaColumn = 1;
                break;
        }

        boolean objectFound = false;
        int row = character.getMapElement().getRow();
        int column = character.getMapElement().getColumn();

        while (!objectFound)
        {
            row += deltaRow;
            column += deltaColumn;

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
                enemy.damage(damage, character, gameWorld);
                System.out.println("After: " + enemy.getHP());

                int distance = 1 + (int)Math.floor(spellPoints / 4);
                System.out.println("Maximum distance: " + distance);

                row = element.getRow();
                column = element.getColumn();

                for (int i = 0; i < distance; i++)
                {
                    row += deltaRow;
                    column += deltaColumn;

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

    public boolean requiresDirectionInput()
    {
        return true;
    }
}

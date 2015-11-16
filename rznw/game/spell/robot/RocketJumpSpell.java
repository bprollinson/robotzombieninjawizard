package rznw.game.spell.robot;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.Wall;

public class RocketJumpSpell extends Spell
{
    public boolean canCast(MainCharacter character)
    {
        return character.getSpellPoints(5) > 0 && character.getMP() >= this.getMPCost(character);
    }

    public void cast(GameWorld gameWorld)
    {
    }

    public void cast(GameWorld gameWorld, int direction)
    {
        System.out.println("Casting Rocket Jump");

        MainCharacter character = gameWorld.getMainCharacter();

        int startRow = character.getMapElement().getRow();
        int startColumn = character.getMapElement().getColumn();

        Map map = gameWorld.getMap();
        map.setElement(startRow, startColumn, null);

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

        int row = startRow;
        int column = startColumn;

        while (!(map.getElement(row, column) instanceof Wall))
        {
            row += deltaRow;
            column += deltaColumn;
        }

        row -= deltaRow;
        column -= deltaColumn;

        while (map.getElement(row, column) != null)
        {
            row -= deltaRow;
            column -= deltaColumn;
        }

        MapElement characterElement = character.getMapElement();
        characterElement.setRow(row);
        characterElement.setColumn(column);
        map.setElement(row, column, characterElement);
    }

    public int getMPCost(MainCharacter character)
    {
        int spellLevel = character.getSpellPoints(5);
        return Math.max(200 - 10 * spellLevel, 1);
    }

    public boolean requiresDirectionInput()
    {
        return true;
    }
}

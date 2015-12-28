package rznw.game.spell.robot;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;

public class RocketPackSpell extends DirectedSpell
{
    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Rocket Pack");

        MainCharacter character = gameWorld.getMainCharacter();

        int startRow = character.getMapElement().getRow();
        int startColumn = character.getMapElement().getColumn();

        Map map = gameWorld.getMap();
        map.setElement(startRow, startColumn, null);

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(0, 0, direction);

        int row = startRow;
        int column = startColumn;

        while (map.getElement(row, column) == null)
        {
            row += positionChange.getDeltaRow();
            column += positionChange.getDeltaColumn();
        }

        row -= positionChange.getDeltaRow();
        column -= positionChange.getDeltaColumn();

        MapElement characterElement = character.getMapElement();
        characterElement.setRow(row);
        characterElement.setColumn(column);
        map.setElement(row, column, characterElement);
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints)
        };
    }
}

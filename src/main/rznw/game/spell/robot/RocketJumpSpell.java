package rznw.game.spell.robot;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapElementSetter;
import rznw.map.element.MapElement;
import rznw.map.element.Void;
import rznw.map.element.Wall;
import rznw.turn.positionchange.SpellBasedPositionChange;
import rznw.ui.LogRendererFactory;

public class RocketJumpSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Rocket Jump";
    }

    public String getDescription()
    {
        return "Allows you to zip across the map, jumping over obstacles in your path.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        LogRendererFactory.instance().log("Casting rocket jump.");

        MainCharacter character = gameWorld.getMainCharacter();

        int startRow = character.getMapElement().getRow();
        int startColumn = character.getMapElement().getColumn();

        Map map = gameWorld.getMap();
        map.setElement(startRow, startColumn, null);

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(direction);

        int row = startRow;
        int column = startColumn;

        while (!(map.getElement(row, column) instanceof Wall) && !(map.getElement(row, column) instanceof Void))
        {
            row += positionChange.getDeltaRow();
            column += positionChange.getDeltaColumn();
        }

        row -= positionChange.getDeltaRow();
        column -= positionChange.getDeltaColumn();

        while (map.getElement(row, column) != null)
        {
            row -= positionChange.getDeltaRow();
            column -= positionChange.getDeltaColumn();
        }

        MapElementSetter.setElement(map, character.getMapElement(), row, column);
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

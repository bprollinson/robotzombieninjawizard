package rznw.game.spell.robot;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapElementSetter;
import rznw.map.MapRayTracer;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;

public class RocketPackSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Rocket Pack";
    }

    public String getDescription()
    {
        return "Allows you to zip across the map in a straight line.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Rocket Pack");

        MainCharacter character = gameWorld.getMainCharacter();
        MapElement characterElement = character.getMapElement();

        Map map = gameWorld.getMap();
        map.setElement(characterElement.getRow(), characterElement.getColumn(), null);

        MapElement element = new MapRayTracer(map).findNextElementInDirection(characterElement, direction);

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(direction);
        int row = element.getRow() - positionChange.getDeltaRow();
        int column = element.getColumn() - positionChange.getDeltaColumn();

        MapElementSetter.setElement(map, characterElement, row, column);
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

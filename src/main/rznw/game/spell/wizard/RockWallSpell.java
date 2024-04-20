package rznw.game.spell.wizard;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.RockWall;
import rznw.ui.LogRendererFactory;

public class RockWallSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Rock Wall";
    }

    public String getDescription()
    {
        return "Generates a barrier on any side of you, protecting you from enemeny attack. The length, distance and duration of the barrier depend on your spell level. The wall can only be damaged by melee attack but cannot dodge attacks.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        LogRendererFactory.instance().log("Casting rock wall.");

        MainCharacter character = gameWorld.getMainCharacter();

        int wallWidth = 1 + 2 * (int)Math.floor(spellPoints / 4);
        int wallDistance = Math.max(1, 5 - (int)Math.floor(spellPoints / 4));
        int wallHP = 10 * spellPoints;

        int deltaRow = 0;
        int deltaColumn = 0;

        switch(direction)
        {
            case Spell.DIRECTION_UP:
                deltaRow = -1 * wallDistance;
                break;
            case Spell.DIRECTION_DOWN:
                deltaRow = 1 * wallDistance;
                break;
            case Spell.DIRECTION_LEFT:
                deltaColumn = -1 * wallDistance;
                break;
            case Spell.DIRECTION_RIGHT:
                deltaColumn = 1 * wallDistance;
                break;
        }

        MapElement characterMapElement = character.getMapElement();
        int centerRow = characterMapElement.getRow() + deltaRow;
        int centerColumn = characterMapElement.getColumn() + deltaColumn;

        Map map = gameWorld.getMap();

        if (centerRow >= 0 && centerRow < Map.NUM_ROWS && centerColumn >= 0 && centerColumn < Map.NUM_COLUMNS && map.getElement(centerRow, centerColumn) == null)
        {
            RockWall rockWall = new RockWall(centerRow, centerColumn, wallHP);
            map.setElement(centerRow, centerColumn, rockWall);
        }

        deltaRow = 0;
        deltaColumn = 0;

        if (direction == Spell.DIRECTION_UP || direction == Spell.DIRECTION_DOWN)
        {
            deltaColumn = 1;
        }
        else
        {
            deltaRow = 1;
        }

        int sideWidth = (int)Math.floor(spellPoints / 4);

        for (int i = 1; i <= sideWidth; i++)
        {
            int row = centerRow + i * deltaRow;
            int column = centerColumn + i * deltaColumn;

            if (row >= 0 && row < Map.NUM_ROWS && column >= 0 && column < Map.NUM_COLUMNS && map.getElement(row, column) == null)
            {
                RockWall rockWall = new RockWall(row, column, wallHP);
                map.setElement(row, column, rockWall);
            }
        }

        for (int i = 1; i <= sideWidth; i++)
        {
            int row = centerRow - i * deltaRow;
            int column = centerColumn - i * deltaColumn;

            if (row >= 0 && row < Map.NUM_ROWS && column >= 0 && column < Map.NUM_COLUMNS && map.getElement(row, column) == null)
            {
                RockWall rockWall = new RockWall(row, column, wallHP);
                map.setElement(row, column, rockWall);
            }
        }

        LogRendererFactory.instance().log("Rock wall created.");
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int wallWidth = 1 + 2 * (int)Math.floor(spellPoints / 4);
        int wallDistance = Math.max(1, 5 - (int)Math.floor(spellPoints / 4));

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Wall width: " + wallWidth,
            "Wall distance: " + wallDistance,
            "Wall HP: " + 10 * spellPoints
        };
    }
}

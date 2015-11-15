package rznw.game.spell.wizard;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.RockWall;

public class RockWallSpell extends Spell
{
    public boolean canCast(MainCharacter character)
    {
        return character.getSpellPoints(0) > 0 && character.getMP() >= this.getMPCost(character);
    }

    public void cast(GameWorld gameWorld)
    {
    }

    public void cast(GameWorld gameWorld, int direction)
    {
        System.out.println("Casting Rock Wall");

        MainCharacter character = gameWorld.getMainCharacter();
        character.setMP(character.getMP() - this.getMPCost(character));

        int wallWidth = 1 + 2 * (int)Math.floor(character.getSpellPoints(0) / 4);
        System.out.println("Wall width: " + wallWidth);
        int wallDistance = Math.max(1, 5 - (int)Math.floor(character.getSpellPoints(0) / 4));
        System.out.println("Wall distance: " + wallDistance);
        int wallHP = 10 * character.getSpellPoints(0);
        System.out.println("Wall HP: " + wallHP);

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

        if (map.getElement(centerRow, centerColumn) == null)
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

        int sideWidth = (int)Math.floor(character.getSpellPoints(0) / 4);

        for (int i = 1; i <= sideWidth; i++)
        {
            int row = centerRow + i * deltaRow;
            int column = centerColumn + i * deltaColumn;

            if (map.getElement(row, column) == null)
            {
                RockWall rockWall = new RockWall(row, column, wallHP);
                map.setElement(row, column, rockWall);
            }
        }

        for (int i = 1; i <= sideWidth; i++)
        {
            int row = centerRow - i * deltaRow;
            int column = centerColumn - i * deltaColumn;

            if (map.getElement(row, column) == null)
            {
                RockWall rockWall = new RockWall(row, column, wallHP);
                map.setElement(row, column, rockWall);
            }
        }
    }

    private int getMPCost(MainCharacter character)
    {
        int spellLevel = character.getSpellPoints(0);
        return Math.max(200 - 10 * spellLevel, 1);
    }

    public boolean requiresDirectionInput()
    {
        return true;
    }
}
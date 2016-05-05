package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.Waypoint;
import rznw.utility.RandomNumberGenerator;

public class WaypointSkill extends Skill
{
    public String getDisplayName()
    {
        return "Waypoint";
    }

    public String getDescription()
    {
        return "Sets up a waypoint or allows you to return to one.";
    }

    public boolean canUse(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        if (character.getSkillPoints(5) == 0)
        {
            return false;
        }

        boolean waypointDeployed = this.waypointDeployed(gameWorld);

        return waypointDeployed && !this.waypointCovered(gameWorld) || !waypointDeployed && !this.currentSquareHasBackground(gameWorld);
    }

    public void use(GameWorld gameWorld)
    {
        System.out.println("Using Waypoint");

        int successProbability = 20 + 5 * gameWorld.getMainCharacter().getSkillPoints(5);
        if (!RandomNumberGenerator.rollSucceeds(successProbability))
        {
            System.out.println("Failure");
            return;
        }

        System.out.println("Success");

        boolean waypointDeployed = this.waypointDeployed(gameWorld);

        if (waypointDeployed && !this.waypointCovered(gameWorld))
        {
            Map map = gameWorld.getMap();

            int waypointRow = -1;
            int waypointColumn = -1;

            for (int row = 0; row < Map.NUM_ROWS; row++)
            {
                for (int column = 0; column < Map.NUM_COLUMNS; column++)
                {
                    MapElement element = map.getBackgroundElement(row, column);

                    if (element instanceof Waypoint)
                    {
                        waypointRow = row;
                        waypointColumn = column;
                    }
                }
            }

            map.setBackgroundElement(waypointRow, waypointColumn, null);

            int characterRow = gameWorld.getMainCharacter().getMapElement().getRow();
            int characterColumn = gameWorld.getMainCharacter().getMapElement().getColumn();
            map.setElement(characterRow, characterColumn, null);

            MapElement characterElement = gameWorld.getMainCharacter().getMapElement();
            characterElement.setRow(waypointRow);
            characterElement.setColumn(waypointColumn);
            map.setElement(waypointRow, waypointColumn, characterElement);
        }
        else if (!waypointDeployed && !this.currentSquareHasBackground(gameWorld))
        {
            MapElement characterMapElement = gameWorld.getMainCharacter().getMapElement();
            Waypoint waypoint = new Waypoint(characterMapElement.getRow(), characterMapElement.getColumn());

            Map map = gameWorld.getMap();
            map.setBackgroundElement(characterMapElement.getRow(), characterMapElement.getColumn(), waypoint);
        }
    }

    private boolean waypointDeployed(GameWorld gameWorld)
    {
        Map map = gameWorld.getMap();

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getBackgroundElement(row, column);

                if (element instanceof Waypoint)
                {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean waypointCovered(GameWorld gameWorld)
    {
        int waypointRow = -1;
        int waypointColumn = -1;

        Map map = gameWorld.getMap();

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getBackgroundElement(row, column);

                if (element instanceof Waypoint)
                {
                    waypointRow = row;
                    waypointColumn = column;
                }
            }
        }

        if (waypointRow == -1)
        {
            return false;
        }

        return map.getElement(waypointRow, waypointColumn) != null;
    }

    private boolean currentSquareHasBackground(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();
        MapElement characterElement = character.getMapElement();

        Map map = gameWorld.getMap();
        MapElement backgroundElement = map.getBackgroundElement(characterElement.getRow(), characterElement.getColumn());

        return backgroundElement != null;
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        int successProbability = 20 + 5 * skillPoints;

        return new String[] {
            "Success probability: " + successProbability + "%"
        };
    }
}

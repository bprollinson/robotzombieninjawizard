package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.MapElementSetter;
import rznw.map.MapScraper;
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

        if (character.getSkills().getSkillPoints(Skill.SKILL_WAYPOINT) == 0)
        {
            return false;
        }

        boolean waypointDeployed = this.waypointDeployed(gameWorld);

        return waypointDeployed && !this.waypointCovered(gameWorld) || !waypointDeployed && !this.currentSquareHasBackground(gameWorld);
    }

    public void use(GameWorld gameWorld)
    {
        System.out.println("Using Waypoint");

        int successProbability = 20 + 5 * gameWorld.getMainCharacter().getSkills().getSkillPoints(Skill.SKILL_WAYPOINT);
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
            MapScraper mapScraper = new MapScraper();

            MapElement waypoint = mapScraper.getFirstBackgroundElementOfType(map, Waypoint.class);
            map.setBackgroundElement(waypoint.getRow(), waypoint.getColumn(), null);

            MapElement characterElement = gameWorld.getMainCharacter().getMapElement();
            map.setElement(characterElement.getRow(), characterElement.getColumn(), null);

            MapElementSetter.setElement(map, characterElement, waypoint.getRow(), waypoint.getColumn());
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
        MapScraper mapScraper = new MapScraper();
        Map map = gameWorld.getMap();

        return mapScraper.mapContainsBackgroundElementOfType(map, Waypoint.class);
    }

    private boolean waypointCovered(GameWorld gameWorld)
    {
        Map map = gameWorld.getMap();
        MapScraper mapScraper = new MapScraper();

        MapElement waypoint = mapScraper.getFirstBackgroundElementOfType(map, Waypoint.class);

        if (waypoint == null)
        {
            return false;
        }

        return map.getElement(waypoint.getRow(), waypoint.getColumn()) != null;
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

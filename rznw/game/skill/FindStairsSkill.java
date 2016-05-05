package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.element.MapElement;
import rznw.map.element.Stairs;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.utility.RandomNumberGenerator;

public class FindStairsSkill extends PassiveSkill
{
    public String getDisplayName()
    {
        return "Find Stairs";
    }

    public String getDescription()
    {
        return "Shows you the position of the nearest set of stairs within your available radius.";
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Radius: " + 3 * skillPoints
        };
    }
}

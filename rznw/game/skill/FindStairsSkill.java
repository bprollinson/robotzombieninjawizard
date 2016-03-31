package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.element.MapElement;
import rznw.map.element.Stairs;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.utility.RandomNumberGenerator;

public class FindStairsSkill extends PassiveSkill
{
    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Radius: " + 3 * skillPoints
        };
    }
}

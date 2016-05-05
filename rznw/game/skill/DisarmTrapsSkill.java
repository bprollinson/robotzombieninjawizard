package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class DisarmTrapsSkill extends PassiveSkill
{
    public String getDisplayName()
    {
        return "Disarm Traps";
    }

    public String getDescription()
    {
        return "Has a chance to automatically disarm traps you encounter.";
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Disarm probability: " + 5 * skillPoints + "%"
        };
    }
}

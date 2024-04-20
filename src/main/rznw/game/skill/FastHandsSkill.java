package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class FastHandsSkill extends PassiveSkill
{
    public String getDisplayName()
    {
        return "Fast Hands";
    }

    public String getDescription()
    {
        return "Increases enemy item drop rate.";
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Extra item probability: " + 2 * skillPoints + "%"
        };
    }
}

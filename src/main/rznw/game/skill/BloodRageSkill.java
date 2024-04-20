package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class BloodRageSkill extends PassiveSkill
{
    public String getDisplayName()
    {
        return "Blood Rage";
    }

    public String getDescription()
    {
        return "Increases the damage you deal when injured.";
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Bonus damage %: " + skillPoints + "% of missing HP %"
        };
    }
}

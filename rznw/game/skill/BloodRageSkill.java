package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class BloodRageSkill extends PassiveSkill
{
    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Bonus damage %: " + skillPoints + "% of missing HP %"
        };
    }
}

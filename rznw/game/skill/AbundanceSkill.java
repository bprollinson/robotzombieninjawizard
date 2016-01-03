package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class AbundanceSkill extends PassiveSkill
{
    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Bonus gold: 0 - " + skillPoints + "%"
        };
    }
}

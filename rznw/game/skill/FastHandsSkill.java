package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class FastHandsSkill extends PassiveSkill
{
    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Extra item probability: " + 2 * skillPoints + "%"
        };
    }
}
